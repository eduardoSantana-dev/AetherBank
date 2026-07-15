
package com.database.model

import  jakarta.persistence.*
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "Transferencia")
data class TransferenciaModel(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @ManyToOne
    val pagador: ContaModel,
    @ManyToOne
    val favorecido: ContaModel,
    @Column(precision = 10, scale = 2)
    val valor : BigDecimal = BigDecimal.ZERO,
    val dataTrans: LocalDateTime = LocalDateTime.now()
)