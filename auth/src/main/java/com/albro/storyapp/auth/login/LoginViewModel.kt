package com.albro.storyapp.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.albro.storyapp.core.domain.models.Login
import com.albro.storyapp.core.domain.usecases.auth.interfaces.LoginUseCase
import com.albro.storyapp.core.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {
    suspend fun login(email: String, password: String): LiveData<UiState<Login>> {
        val requestBody = hashMapOf<String, String>()
        requestBody["email"] = email
        requestBody["password"] = password
        return loginUseCase.execute(requestBody).asLiveData()
    }

    fun loginSuccessful() {
        viewModelScope.launch {
            loginUseCase.setLoginState(true)
        }
    }

    fun saveTokenKey(token: String) {
        viewModelScope.launch {
            loginUseCase.storeTokenKey(token)
        }
    }
}