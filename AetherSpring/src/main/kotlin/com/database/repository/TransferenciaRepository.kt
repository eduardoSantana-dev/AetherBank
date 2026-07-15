package com.database.repository

import com.database.model.TransferenciaModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TransferenciaRepository: JpaRepository<TransferenciaModel, Long> {

}