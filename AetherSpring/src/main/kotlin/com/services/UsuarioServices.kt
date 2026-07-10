package com.services

import com.database.model.UsuarioModel
import com.dtos.RegistroRequest
import com.dtos.CredenciaisResponse
import com.dtos.LoginRequest
import com.repository.UsuarioRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import javax.xml.crypto.Data
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder
@Service
class UsuarioServices (private val repository: UsuarioRepository){
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
    fun logar(login: LoginRequest): ResponseEntity<CredenciaisResponse>{
        val encoder = Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8()

        val usuario = repository.findByEmail(login.email)
        val valido = encoder.matches(login.password, usuario?.senha)
        if(valido){
        return ResponseEntity.status(HttpStatus.OK).body(CredenciaisResponse(
            message = "Achamo o cara",
            id = 5,
            nome = usuario?.nome,
            email = usuario?.email,
            cpf = usuario?.cpf,
            numero = usuario?.numero
        ))
        }
        else{
            return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .build()
        }
    }
}