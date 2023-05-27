package com.albro.storyapp.core.data.repositories

import com.albro.storyapp.core.data.source.local.preferences.IDataStoreDataSource
import com.albro.storyapp.core.data.source.remote.RemoteDataSource
import com.albro.storyapp.core.domain.models.Login
import com.albro.storyapp.core.domain.models.Register
import com.albro.storyapp.core.domain.repositories.IAuthRepository
import com.albro.storyapp.core.data.source.remote.network.ApiResponse
import com.albro.storyapp.core.utils.UiState
import com.albro.storyapp.core.utils.mapToDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val dataStore: IDataStoreDataSource,
    private val remoteDataSource: RemoteDataSource,
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