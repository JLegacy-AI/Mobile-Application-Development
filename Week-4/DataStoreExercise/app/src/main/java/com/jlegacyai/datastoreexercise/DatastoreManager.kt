package com.jlegacyai.datastoreexercise
import androidx.datastore.preferences.preferencesDataStore
import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

object DatastoreManager{
    private val Context.dataStore by preferencesDataStore(name = "jlegacy-ai")
    private val COUNTER = intPreferencesKey("counter")

    suspend fun incrementCoutner(context: Context){
        context.dataStore.edit {
            setting ->
            val previousValue = setting[COUNTER] ?: 0
            setting[COUNTER] = previousValue + 1
        }
    }

    suspend fun getCounterValue(context: Context): Int {
        return context.dataStore.data.map {
            it[COUNTER] ?: 0
        }.first()
    }
}