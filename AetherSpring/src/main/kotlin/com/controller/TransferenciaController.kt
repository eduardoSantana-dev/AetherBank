package com.controller

import com.database.model.UsuarioModel
import com.dtos.TransferenciaRequest
import com.dtos.TransferenciaResponse
import com.services.TransferenciaServices
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/transferencia")
class TransferenciaController(
    private val services: TransferenciaServices
) {
    @PostMapping("/enviar")
    fun enviar (@AuthenticationPrincipal usuario: UsuarioModel,@RequestBody dados: TransferenciaRequest,request: HttpServletRequest): ResponseEntity<TransferenciaResponse>{
        println(usuario)
        println(dados)
        return services.enviar(usuario,dados)
    }
}

//        println("IP: ${request.remoteAddr}")
//        println("Metodo: ${request.method}")
//        println("URI: ${request.requestURI}")
//        println("URL: ${request.requestURL}")
//        println("User-Agent: ${request.getHeader("User-Agent")}")
//        println("Host: ${request.getHeader("Host")}")
//        println("Origin: ${request.getHeader("Origin")}")