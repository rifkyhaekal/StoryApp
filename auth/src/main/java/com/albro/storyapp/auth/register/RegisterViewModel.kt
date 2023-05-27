package com.albro.storyapp.auth.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.albro.storyapp.core.domain.models.Register
import com.albro.storyapp.core.domain.usecases.auth.interfaces.RegisterUseCase
import com.albro.storyapp.core.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val registerUseCase: RegisterUseCase) : ViewModel() {
    suspend fun register(name: String, email: String, password: String): LiveData<UiState<Register>> {
        val params = hashMapOf<String, String>()
        params["name"] = name
        params["email"] = email
        params["password"] = password
        return registerUseCase(params).asLiveData()
    }
}