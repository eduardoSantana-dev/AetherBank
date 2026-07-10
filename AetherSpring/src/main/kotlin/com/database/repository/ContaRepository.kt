package com.repository

import com.database.model.ContaModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
@Repository
interface ContaRepository : JpaRepository<ContaModel, Long> {
    fun findByUsuarioId(id: Long): ContaModel?
}

