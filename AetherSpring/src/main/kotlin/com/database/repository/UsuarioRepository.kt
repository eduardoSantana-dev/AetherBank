package com.repository
import com.database.model.UsuarioModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
@Repository
interface UsuarioRepository: JpaRepository<UsuarioModel,Long>{
    fun findByEmail(email: String): UsuarioModel?
}

