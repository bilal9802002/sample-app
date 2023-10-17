package com.interview.sample.ui.screens.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.interview.sample.data.db.dao.UserDao
import com.interview.sample.domain.entities.PatientEntity
import com.interview.sample.domain.usecases.PatientUseCase
import com.interview.sample.network.resource.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    userDao: UserDao,
    patientUseCase: PatientUseCase
) : ViewModel() {

    private val currentUser = userDao.getUser()

    @OptIn(ExperimentalCoroutinesApi::class)
    val patientData: StateFlow<DashboardUiState> = currentUser.flatMapLatest {
        patientUseCase(it.userId).map { resource ->
            when (resource) {
                is Resource.Loading -> DashboardUiState.Loading
                is Resource.Success -> DashboardUiState.Success(it.username, resource.data)
                is Resource.Error -> DashboardUiState.Error(
                    resource.throwable.message ?: "Something went wrong!"
                )
            }
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = DashboardUiState.Loading,
    )
}

sealed interface DashboardUiState {
    data class Success(val username: String, val patientEntity: PatientEntity) : DashboardUiState
    data class Error(val message: String) : DashboardUiState
    object Loading : DashboardUiState
}