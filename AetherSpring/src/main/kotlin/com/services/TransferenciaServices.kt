package com.services

import com.database.model.UsuarioModel
import com.database.repository.TransferenciaRepository
import com.dtos.BuscarChaveResponse
import com.dtos.TransferenciaRequest
import com.dtos.TransferenciaResponse
import com.repository.ContaRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TransferenciaServices(
    private val repository: TransferenciaRepository,
    private val contaRepository: ContaRepository,
    private val UsuarioServices: UsuarioServices
) {

    @Transactional
    fun enviar(usuario: UsuarioModel,dados: TransferenciaRequest): ResponseEntity<TransferenciaResponse>{
        val favorecido: ResponseEntity<BuscarChaveResponse> = UsuarioServices.buscar(dados.chavePix)
        if(favorecido.statusCode == HttpStatus.OK){
            val usuarioFavorecido = favorecido.body?.usuario
            var contaFavorecido = favorecido.body?.conta
            var contaPagador = contaRepository.findByUsuarioId(usuario.id)?: throw RuntimeException("Estamos com problema para acessar sua conta")
            if(contaPagador.saldo < dados.valor){
                throw RuntimeException("Saldo insuficiente")
            }
            contaPagador.saldo -= dados.valor
            contaFavorecido?.saldo += dados.valor

            return ResponseEntity.ok().body(
                TransferenciaResponse(
                    concluida = true,
                    mensagem = "Tranferência de ${dados.valor} realizada para ${usuarioFavorecido?.nome}",
                    valor= dados.valor,
                    user = "${usuarioFavorecido?.cpf}"
                ))
        }else{
             throw RuntimeException("Conta não encontrada")
        }
        return ResponseEntity.unprocessableContent().build()


    }
}