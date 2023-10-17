package com.interview.sample.ui.screens.login

import androidx.lifecycle.ViewModel
import com.interview.sample.domain.usecases.LoginUseCase
import com.interview.sample.network.resource.Resource
import com.interview.sample.ui.common.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase): ViewModel() {

    val loginResponse = MutableStateFlow<LoginUiState>(LoginUiState.NotInit)

    fun login(userName: String, password: String) {
        launch {
            loginUseCase.invoke(userName, password).collect {
                when (it) {
                    is Resource.Loading -> {
                        loginResponse.value = LoginUiState.Loading
                    }
                    is Resource.Success -> {
                        loginResponse.value = LoginUiState.Success(it.data.userId)
                    }
                    is Resource.Error -> {
                        loginResponse.value = LoginUiState.Error
                    }
                }
            }
        }
    }
}

sealed interface LoginUiState {
    data class Success(val userId: String): LoginUiState
    object Error: LoginUiState
    object Loading: LoginUiState
    object NotInit: LoginUiState
}