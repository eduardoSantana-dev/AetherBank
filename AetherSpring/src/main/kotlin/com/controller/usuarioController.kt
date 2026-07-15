package com.controller

import com.database.model.ContaModel
import com.database.model.UsuarioModel
import com.dtos.BuscarChaveResponse
import com.dtos.CredenciaisResponse
import com.dtos.LoginRequest
import com.dtos.LoginResponse
import com.dtos.RegistroRequest
import com.repository.UsuarioRepository
import com.services.UsuarioServices
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/usuarios")
class ApiUser(private val services: UsuarioServices){
    @GetMapping("/validar")
    fun validar(): ResponseEntity<Void>{
        return ResponseEntity.ok().build()
    }

        @PostMapping("/auth/registrar")
        fun registrar(@RequestBody user : RegistroRequest): ResponseEntity<CredenciaisResponse>{
            return services.registrar(user)
        }
    @PostMapping("/auth/login")
    fun login(@RequestBody  login : LoginRequest) : ResponseEntity<LoginResponse>{
        return  services.logar(login)
    }
    @GetMapping("/teste")
    fun teste(
        @AuthenticationPrincipal usuario: UsuarioModel,
        ): UsuarioModel{
        return usuario
    }
    @GetMapping("/buscar/{chave}")
    fun buscar(@PathVariable chave: String): ResponseEntity<BuscarChaveResponse>{
        return services.buscar(chave)
    }
}

