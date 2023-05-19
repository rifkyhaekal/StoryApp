package com.albro.core.data.repositories

import com.albro.core.data.source.local.preferences.IDataStoreDataSource
import com.albro.core.data.source.remote.IRemoteDataSource
import com.albro.core.domain.models.Login
import com.albro.core.domain.models.Register
import com.albro.core.domain.repositories.IAuthRepository
import com.albro.core.utils.ApiResponse
import com.albro.core.utils.UiState
import com.albro.core.utils.mapToDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

class AuthRepository(
    private val dataStore: IDataStoreDataSource,
    private val remoteDataSource: IRemoteDataSource,
) : IAuthRepository {
    override suspend fun setLoginState(state: Boolean) = dataStore.setLoginState(state)

    override suspend fun setTokenKey(tokenKey: String) = dataStore.setTokenKey(tokenKey)

    override suspend fun deleteTokenKey() = dataStore.deleteTokenKey()

    override fun getLoginState(): Flow<Boolean> = dataStore.getLoginState()

    override fun getTokenKey(): Flow<String> = dataStore.getTokenKey()

    override suspend fun login(requestBody: HashMap<String, String>): Flow<UiState<Login>> =
        flow {
            remoteDataSource.login(requestBody).onStart {
                emit(UiState.loading())
            }.collect { response ->
                emit(UiState.hideLoading())
                when (response) {
                    is ApiResponse.Success -> {
                        emit(UiState.success(response.data.mapToDomain()))
                    }

                    is ApiResponse.Error -> {
                        emit(UiState.error(message = response.errorMessage))
                    }
                }
            }
        }

    override suspend fun register(requestBody: HashMap<String, String>): Flow<UiState<Register>> =
        flow {
            remoteDataSource.register(requestBody).onStart {
                emit(UiState.loading())
            }.collect { response ->
                emit(UiState.hideLoading())
                when (response) {
                    is ApiResponse.Success -> {
                        emit(UiState.success(response.data.mapToDomain()))
                    }

                    is ApiResponse.Error -> {
                        emit(UiState.error(message = response.errorMessage))
                    }
                }
            }
        }
}