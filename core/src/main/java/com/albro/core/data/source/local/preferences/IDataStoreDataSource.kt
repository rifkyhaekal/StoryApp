package com.albro.core.data.source.local.preferences

import kotlinx.coroutines.flow.Flow

interface IDataStoreDataSource {
    suspend fun setLoginState(state: Boolean)
    fun getLoginState(): Flow<Boolean>
    suspend fun setTokenKey(token: String)
    suspend fun deleteTokenKey()
    fun getTokenKey(): Flow<String>
}