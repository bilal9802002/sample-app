package com.interview.sample.ui.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.interview.sample.domain.entities.AssociatedDrugEntity
import com.interview.sample.ui.common.TitleValueVariant

@Composable
fun DrugDetailRoute(associatedDrugEntity: AssociatedDrugEntity, backOnClick: () -> Unit) {
    DrugDetailScreen(associatedDrugEntity, backOnClick)
}

@Preview
@Composable
internal fun DrugDetailScreen(
    associatedDrugEntity: AssociatedDrugEntity = AssociatedDrugEntity(
        "Asprin",
        "1 tablet daily",
        "500 mg"
    ),
    backOnClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(20.dp)
    ) {
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "Drug Info",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline
            )
        )
        Column(
            modifier = Modifier
                .padding(top = 50.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surfaceVariant, MaterialTheme.shapes.small)
                .padding(20.dp)
        ) {
            TitleValueVariant(
                modifier = Modifier.padding(vertical = 10.dp),
                title = "Name: ",
                value = associatedDrugEntity.name
            )
            TitleValueVariant(
                modifier = Modifier.padding(vertical = 10.dp),
                title = "Dose: ",
                value = associatedDrugEntity.dose
            )
            TitleValueVariant(
                modifier = Modifier.padding(vertical = 10.dp),
                title = "Strength: ",
                value = associatedDrugEntity.strength
            )
        }
        Box(modifier = Modifier.fillMaxSize()) {
            Button(modifier = Modifier.align(Alignment.BottomCenter), onClick = backOnClick) {
                Text(text = "Done", style = MaterialTheme.typography.labelLarge)
            }
        }
    }
}