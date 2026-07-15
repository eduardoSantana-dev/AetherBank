package com.services


import com.database.model.ContaModel
import com.database.model.UsuarioModel
import com.dtos.ContaResponse
import com.dtos.PagamentoResponse
import com.repository.ContaRepository
import com.repository.UsuarioRepository
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale

@Service
class ContaServices (
    private val repository : ContaRepository,
    private val UsuarioRepository: UsuarioRepository
){
    fun formatarSaldo(saldo: BigDecimal): String{
        val formatacao = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
        return formatacao.format(saldo)
    }
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
           val saldoFormatado = formatarSaldo(conta.saldo)
            return ResponseEntity.ok(ContaResponse(saldo = saldoFormatado))

        }else{
            return ResponseEntity.notFound().build()
        }
    }
    fun buscar(usuario: UsuarioModel): ContaModel?{
            return  repository.findByUsuarioId(usuario.id)
    }
    fun inforPagamento(usuario: UsuarioModel,chave: String): ResponseEntity<PagamentoResponse>{
            val saldo = buscar(usuario)?.saldo?:
            return ResponseEntity.notFound().build()

            val saldoFormatado = formatarSaldo(saldo)
            val favorecido = UsuarioRepository.findByEmailOrNumeroOrCpf(chave,chave,chave)?:
            return ResponseEntity.notFound().build()

            return ResponseEntity.ok().body(PagamentoResponse(favorecido.nome,saldoFormatado))


    }

}