package com.repository
import com.database.model.UsuarioModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Repository
@Repository
interface UsuarioRepository: JpaRepository<UsuarioModel,Long>{
    fun findByCpf(cpf: String): UsuarioModel
    fun findByEmail(email: String): UsuarioModel?
    fun findByEmailOrNumeroOrCpf(
        email: String,
        numero: String,
        cpf: String
    ): UsuarioModel?
}

