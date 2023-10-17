package com.interview.sample.ui.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.interview.sample.R
import com.interview.sample.ui.common.PasswordInputField
import com.interview.sample.ui.common.TextInputField


@Composable
fun LoginRoute(
    viewModel: LoginViewModel = hiltViewModel(),
    navigateCallback: (userId: String) -> Unit
) {
    val loginUiState: LoginUiState by viewModel.loginResponse.collectAsStateWithLifecycle()

    if (loginUiState is LoginUiState.Success) {
        navigateCallback((loginUiState as LoginUiState.Success).userId)
    } else {
        LoginScreen(
            loginUiState,
        ) { username, password ->
            viewModel.login(username, password)
        }
    }
}

@Composable
internal fun LoginScreen(
    loginUiState: LoginUiState,
    loginOnClick: (username: String, password: String) -> Unit
) {
    if (loginUiState == LoginUiState.Loading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    } else {
        val userName = remember {
            mutableStateOf("")
        }

        val password = remember {
            mutableStateOf("")
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.login_screen_title),
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(bottom = 40.dp)
            )
            TextInputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                value = userName,
                placeholder = stringResource(id = R.string.user_name_text_field_placeholder),
                isError = loginUiState == LoginUiState.Error
            )
            PasswordInputField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(top = 20.dp),
                value = password,
                placeholder = stringResource(id = R.string.password_text_field_placeholder),
                isError = loginUiState == LoginUiState.Error
            )
            Button(
                onClick = {
                    loginOnClick(userName.value, password.value)
                },
                modifier = Modifier.padding(top = 20.dp),
                enabled = userName.value.isNotBlank() && password.value.isNotBlank()
            ) {
                Text(text = stringResource(id = R.string.login_button_text))
            }
        }
    }
}
