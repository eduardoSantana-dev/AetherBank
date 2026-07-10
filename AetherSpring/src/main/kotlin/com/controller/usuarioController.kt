package com.controller

import com.dtos.CredenciaisResponse
import com.dtos.LoginRequest
import com.dtos.RegistroRequest
import com.repository.UsuarioRepository
import com.services.UsuarioServices
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

class usuario(
    val nome: String,
    val idade: Int
)
@RestController
class ApiUser(private val services: UsuarioServices){
        @PostMapping("/usuarios/registrar")
        fun registrar(@RequestBody user : RegistroRequest): ResponseEntity<CredenciaisResponse>{
            return services.registrar(user)
        }
    @PostMapping("/usuarios/login")
    fun login(@RequestBody login : LoginRequest) : ResponseEntity<CredenciaisResponse>{
        return  services.logar(login)
    }
}