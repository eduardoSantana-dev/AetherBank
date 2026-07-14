package com.security

import com.repository.UsuarioRepository
import com.services.TokenService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class SecurityFiltro (): OncePerRequestFilter() {
    @Autowired
    lateinit var tokenService: TokenService
    @Autowired
    lateinit var repository: UsuarioRepository
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        var token = this.recoverToken(request)
        if (token != null) {
            var login = tokenService.validarToken(token)
            val user = repository.findByEmail(login)
                ?: throw UsernameNotFoundException("Usuário não encontrado")
            var auth = UsernamePasswordAuthenticationToken(user, null, user.authorities)
            SecurityContextHolder.getContext().setAuthentication(auth)
        }
            filterChain.doFilter(request, response)
    }
    private fun recoverToken(request: HttpServletRequest): String? {
        val authHeader = request.getHeader("Authorization")

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null
        }

        return authHeader.replace("Bearer ", "")
    }
}
