package com.services

import com.database.model.UsuarioModel
import com.dtos.RegistroRequest
import com.dtos.CredenciaisResponse
import com.dtos.LoginRequest
import com.dtos.LoginResponse
import com.repository.UsuarioRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service
import javax.xml.crypto.Data
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder

@Service
class UsuarioServices (
    private val repository: UsuarioRepository,
    private val authenticationManager: AuthenticationManager,
    private val tokenService: TokenService
){
    fun registrar(user: RegistroRequest): ResponseEntity<CredenciaisResponse>{
        val encoder = Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8()
        val senha = encoder.encode(user.senha)
        val novoUser = UsuarioModel(
            nome = user.nome,
            email = user.email,
            cpf = user.cpf,
            numero = user.numero,
            senha = senha!!,
            chaveAleatoria = null
        )
        val resposta = repository.save(novoUser)
        return ResponseEntity.status(HttpStatus.OK).body(CredenciaisResponse(
            message = "Usuario cadastrado com sucesso",
            id = resposta.id,
            nome = resposta.nome,
            email = resposta.email,
            cpf = resposta.cpf,
            numero = resposta.numero
        ))
    }
    fun logar(login: LoginRequest): ResponseEntity<LoginResponse>{
        val encoder = Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8()
        val usernamePassword = UsernamePasswordAuthenticationToken(
            login.email,
            login.password
        )
        val auth = authenticationManager.authenticate(usernamePassword)
        var token = tokenService.gerarToken(auth.principal as UsuarioModel)
        return ResponseEntity.status(HttpStatus.OK).body(LoginResponse(
            message = "Usuario logado com sucesso",
            token = token
        ))


    }
}