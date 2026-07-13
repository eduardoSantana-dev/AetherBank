package com.example.aetherbankapp_eduardo.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow

import kotlinx.coroutines.flow.map
private val Context.dataStore by preferencesDataStore(name = "settings")


class DataStoreConfig( private val context: Context) {
    private val tokenKey  = stringPreferencesKey("token")

   fun getToken(): Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[tokenKey]
    }
    suspend fun salvarToken(token: String){
        context.dataStore.edit { preferences ->
            preferences[tokenKey] = token
        }
    }
    suspend fun limparToken() {
        context.dataStore.edit { preferences ->
            preferences.remove(tokenKey)
        }
    }

}