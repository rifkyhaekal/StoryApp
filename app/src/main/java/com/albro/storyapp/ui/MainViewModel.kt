package com.albro.storyapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.albro.storyapp.core.domain.usecases.auth.interfaces.GetLoginStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getLoginStateUseCase: GetLoginStateUseCase) : ViewModel() {
    fun readLoginState(): LiveData<Boolean> = getLoginStateUseCase().asLiveData()
}