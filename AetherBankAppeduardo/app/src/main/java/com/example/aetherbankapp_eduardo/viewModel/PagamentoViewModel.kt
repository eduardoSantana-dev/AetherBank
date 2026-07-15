package com.example.aetherbankapp_eduardo.viewModel

import android.app.Application
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.dtos.TransferenciaRequest
import com.example.aetherbankapp_eduardo.dataStore.DataStoreConfig
import com.example.aetherbankapp_eduardo.retrofit.Repository
import com.google.gson.Gson
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class PagamentoViewModel (application: Application) : AndroidViewModel(application)  {

    private val repository = Repository()
    private val dataStoreConfig = DataStoreConfig(application)
    var chave by mutableStateOf("")
        private set
    var token by mutableStateOf("")
        private set
    var saldo by mutableStateOf("")
        private set
    var nomeFavorecido by mutableStateOf("")
        private set
    var mostrarErro by mutableStateOf(false)
        private set
    var mostrarErroTrans by mutableStateOf(false)
        private set

    var textErro by mutableStateOf("")
        private set

    var mostrarSucesso by mutableStateOf(false)
        private set

    var textSucesso by mutableStateOf("")
        private set
    var inputValue by mutableStateOf("")
        private set

    fun onChangeValue(valor: String){
        inputValue = valor
    }
        fun getChave(valor: String){
            chave = valor
        }
        init {
            viewModelScope.launch {
                    token = dataStoreConfig.getToken().first() ?: ""
                    val resposta = repository.infoPagar(token = "Bearer $token",chave)
                if(resposta.isSuccessful ){
                    saldo = resposta.body()?.saldo?:""
                    nomeFavorecido = resposta.body()?.nomeFavorecido?:""

                }else{
                    mostrarErro = true
                }
                }
        }

    data class ErroResponse(
        val mensagem: String
    )
    fun transferir(){
        val valor = inputValue.replace(",",".").toBigDecimalOrNull()
        if(valor !=null){
            viewModelScope.launch {
              try {
                  val resposta = repository.enviarTrans(TransferenciaRequest(chave,valor),"Bearer $token")
                  val dados = resposta.body()

                  if(resposta.isSuccessful &&  dados?.concluida == true){
                      textSucesso = dados.mensagem
                      mostrarSucesso = true
                  }else{
                      val erroJson = resposta.errorBody()?.string()

                      val erro = Gson().fromJson(
                          erroJson,
                          ErroResponse::class.java
                      )
                      textErro = erro.mensagem
                      mostrarErroTrans = true
                  }
              }catch (erro: Exception){
                  val erroMsg = erro.toString()
                  Log.e("PagamentoViewModel","Erro ao validar token",erro)
                  textErro = erroMsg
                  mostrarErroTrans = true
              }
            }
        }else{
            textErro ="O valor inserido não é valido"
            mostrarErroTrans = true
        }
    }
}