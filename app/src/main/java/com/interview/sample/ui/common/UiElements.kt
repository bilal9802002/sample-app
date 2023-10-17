package com.interview.sample.ui.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextInputField(
    modifier: Modifier,
    placeholder: String,
    value: MutableState<String>,
    isError: Boolean = false
) {
    TextField(
        modifier = modifier,
        value = value.value,
        onValueChange = {
            value.value = it
        },
        placeholder = {
            Text(text = placeholder)
        },
        isError = isError
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordInputField(
    modifier: Modifier,
    placeholder: String,
    value: MutableState<String>,
    isError: Boolean = false
) {
    TextField(
        modifier = modifier,
        value = value.value,
        onValueChange = {
            value.value = it
        },
        visualTransformation = PasswordVisualTransformation(),
        placeholder = {
            Text(text = placeholder)
        },
        isError = isError
    )
}

@Composable
fun TitleValueItem(title: String, value: String) {
    Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
        Text(text = "$title: ", style = MaterialTheme.typography.titleSmall)
        Text(text = value, style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
fun TitleValueVariant(modifier: Modifier = Modifier, title: String, value: String) {
    Text(
        modifier = modifier,
        text = buildAnnotatedString {
            withStyle(
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold
                ).toSpanStyle()
            ) {
                append(title)
            }
            withStyle(
                style = MaterialTheme.typography.labelLarge.toSpanStyle()
            ) {
                append(value)
            }
        }
    )
}