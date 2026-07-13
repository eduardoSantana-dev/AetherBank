
package com.services

import com.repository.UsuarioRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsService (
    private val repository: UsuarioRepository
): UserDetailsService {
    override fun loadUserByUsername(email: String): UserDetails {
        return repository.findByEmail(email)
            ?: throw UsernameNotFoundException("Usuário não encontrado")
    }
}
