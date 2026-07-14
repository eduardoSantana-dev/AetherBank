package com.services


import com.database.model.ContaModel
import com.database.model.UsuarioModel
import com.dtos.ContaResponse
import com.repository.ContaRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.text.NumberFormat
import java.util.Locale

@Service
class ContaServices (
    private val repository : ContaRepository
){
    fun criarConta(user: UsuarioModel):Boolean{
            try {
                repository.save(ContaModel(usuario = user))
                return true
            }catch (e : Exception){
                return false
            }

    }
    fun verSaldo(user: UsuarioModel): ResponseEntity<ContaResponse>{
        val conta = repository.findByUsuarioId(user.id)
        if(conta != null){
            val formatacao = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
            val saldoFormatado = formatacao.format(conta.saldo)
            return ResponseEntity.ok(ContaResponse(saldo = saldoFormatado))

        }else{
            return ResponseEntity.notFound().build()
        }
    }
}