package com.interview.sample.ui.screens.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.interview.sample.domain.entities.AssociatedDrugEntity
import com.interview.sample.ui.common.TitleValueItem
import java.util.Calendar


@Composable
fun DashboardRoute(viewModel: DashboardViewModel = hiltViewModel(), action: (drugEntity: AssociatedDrugEntity) -> Unit) {
    val dashboardUiState by viewModel.patientData.collectAsStateWithLifecycle()

    DashboardScreen(dashboardUiState = dashboardUiState, action)
}

@Composable
internal fun DashboardScreen(dashboardUiState: DashboardUiState, action: (drugEntity: AssociatedDrugEntity) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        when (dashboardUiState) {
            is DashboardUiState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .width(64.dp)
                        .align(Alignment.Center),
                    color = MaterialTheme.colorScheme.surfaceVariant
                )
            }

            is DashboardUiState.Success -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "${getTimeOfDayMessage()} ${dashboardUiState.username}",
                        style = MaterialTheme.typography.headlineSmall
                    )

                    LazyColumn {
                        items(dashboardUiState.patientEntity.problems.flatMap {
                            it.medications.flatMap { it.associatedDrugs }
                        }) {
                            DrugCardItem(it, action)
                        }
                    }
                }
            }

            is DashboardUiState.Error -> {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                        .align(Alignment.Center),
                    text = dashboardUiState.message,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }

    }
}


@Composable
private fun DrugCardItem(drugEntity: AssociatedDrugEntity, action: (drugEntity: AssociatedDrugEntity) -> Unit) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .background(
                MaterialTheme.colorScheme.surfaceVariant,
                RoundedCornerShape(16.dp)
            )
            .padding(8.dp).clickable {
                action(drugEntity)
            }
    ) {
        TitleValueItem("Name", drugEntity.name)
        TitleValueItem("Dose", drugEntity.dose)
        TitleValueItem("Strength", drugEntity.strength)
    }
}

private fun getTimeOfDayMessage(): String {
    val time = Calendar.getInstance()[Calendar.HOUR_OF_DAY]
    return when {
        time <= 12 -> "Good Morning"
        time < 16 -> "Good Afternoon"
        time < 21 -> "Good Evening"
        else -> "Good Night"
    }
}