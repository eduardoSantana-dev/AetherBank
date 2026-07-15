package com.database.model


import  jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "conta")
data class ContaModel(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,
        @ManyToOne
        val usuario: UsuarioModel,
        @Column(precision = 10, scale = 2)
        var saldo : BigDecimal = BigDecimal.ZERO

)