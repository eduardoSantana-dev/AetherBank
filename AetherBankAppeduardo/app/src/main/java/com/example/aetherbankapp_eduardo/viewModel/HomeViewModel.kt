package com.example.aetherbankapp_eduardo.viewModel

import android.app.Application
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dtos.ContaResponse
import com.example.aetherbankapp_eduardo.dataStore.DataStoreConfig
import com.example.aetherbankapp_eduardo.retrofit.Repository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeViewModel (application: Application) : AndroidViewModel(application){
    private val repository = Repository()
    private val dataStoreConfig = DataStoreConfig(application)
    var navegarParaLogin  by  mutableStateOf(false)
        private set
    var saldo by  mutableStateOf("00,00")
        private set

    init {
        viewModelScope.launch {
           try {
               val token = dataStoreConfig.getToken().first() ?: ""
               val resposta: Response<ContaResponse> = repository.verSaldo("Bearer $token")
               if(resposta.isSuccessful){
                    saldo = resposta.body()?.saldo?:""
               }else{
                   Log.e("HomeViewModel", "Erro: ")
                   navegarParaLogin = true
               }
           }catch (erro:Exception){
               Log.e("HomeViewModel","Erro ao buscar saldo",erro)
               navegarParaLogin = true
           }
        }
    }
    var saldoView by  mutableStateOf("••••")
        private set
    fun verSaldo(){
        if(saldoView == "••••"){
            saldoView = saldo
        }else{
            saldoView = "••••"
        }
    }

    fun Logoff(){
        viewModelScope.launch {
            try {
                dataStoreConfig.limparToken()
                navegarParaLogin = true
            }
            catch (erro: Exception){
                Log.e("HomeViewModel","Erro ao sair da conta",erro)
            }
        }
    }

}