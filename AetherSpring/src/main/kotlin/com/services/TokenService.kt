package com.services

import com.auth0.jwt.JWT
import com.database.model.UsuarioModel
import org.springframework.stereotype.Service
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTCreationException
import com.auth0.jwt.exceptions.JWTVerificationException
import org.springframework.beans.factory.annotation.Value
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.Instant

@Service
class TokenService(
    @Value("\${api.security.token.secret}")
    private val secret: String

) {
    fun gerarToken(user: UsuarioModel): String {
            try {
                 val algoritmo = Algorithm.HMAC256(secret)
                var token:String = JWT.create()
                    .withIssuer("aether")
                    .withSubject(user.email)
                    .withExpiresAt(dataDeExpiracao())
                    .sign(algoritmo)
                return token
            }catch (execao: JWTCreationException){
                throw RuntimeException("Erro ao gerar token",execao)

            }
    }
    fun validarToken(token: String): String{
        try {
            val algoritmo = Algorithm.HMAC256(secret)
            return JWT.require(algoritmo)
                .withIssuer("aether")
                .build()
                .verify(token)
                .getSubject()
        }catch(execao: JWTVerificationException){
                return ""
        }
    }
    fun dataDeExpiracao(): Instant{
            return LocalDateTime.now().plusMinutes(3000).toInstant(ZoneOffset.of("-03:00"))
    }
}