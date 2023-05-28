package com.albro.storyapp.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.albro.storyapp.core.domain.usecases.auth.interfaces.LogoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val logoutUseCase: LogoutUseCase) : ViewModel() {
    fun logOut() {
        viewModelScope.launch {
            logoutUseCase()
        }
    }
}