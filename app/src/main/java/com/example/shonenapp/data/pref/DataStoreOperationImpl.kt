package com.example.shonenapp.data.pref

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.shonenapp.domain.respository.DataStoreOperation
import com.example.shonenapp.utils.Constant.ONBOARDING_COMPLETED
import com.example.shonenapp.utils.Constant.SHONEN_PREF
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(SHONEN_PREF)

class DataStoreOperationImpl(context: Context) : DataStoreOperation {

    private object PreferenceKey {
        val onBoardingKey = booleanPreferencesKey(ONBOARDING_COMPLETED)
    }

    private val dataStore = context.dataStore

    override suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.edit { preferenses ->
            preferenses[PreferenceKey.onBoardingKey] = completed
        }
    }

    override fun getOnBoardingState(): Flow<Boolean> {
        return dataStore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferenses ->
            val state = preferenses[PreferenceKey.onBoardingKey] ?: false
            state
        }
    }
}