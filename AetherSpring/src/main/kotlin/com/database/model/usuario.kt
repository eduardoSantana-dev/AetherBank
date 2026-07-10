package com.database.model

import  jakarta.persistence.*

@Entity
@Table(name = "usuario")
data class UsuarioModel(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long = 0,
    val nome: String = "",
    val email: String = "",
    val cpf: String = "",
    val numero: String = "",
    val senha: String = "",
    val chaveAleatoria: String? = "",
)