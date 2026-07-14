package com.controller

import com.database.model.UsuarioModel
import com.dtos.ContaResponse
import com.services.ContaServices
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/conta")
class ContaController(private val services: ContaServices) {

    @GetMapping("/verSaldo")
    fun verSaldo(@AuthenticationPrincipal usuario: UsuarioModel): ResponseEntity<ContaResponse>{
        print(usuario)
        println( services.verSaldo(usuario))
        return services.verSaldo(usuario)
    }
}