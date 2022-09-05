package es.codekai.composer.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import es.codekai.composer.MainViewModel
import es.codekai.composer.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(viewModel: MainViewModel) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
            .clickable { keyboardController?.hide() }
    ) {
        Login(Modifier.align(Alignment.Center), viewModel)
    }
}

@Composable
fun Login(modifier: Modifier, viewModel: MainViewModel) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val uiState: MainViewModel.LoginUIState by viewModel.loginUIState.observeAsState(
        initial = MainViewModel.LoginUIState()
    )

    if (uiState.isLoading) {
        Box(Modifier.fillMaxSize()) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    } else {
        Column(modifier = modifier) {
            Image(
                modifier = modifier,
                painter = painterResource(id = R.drawable.clint2),
                contentDescription = "header"
            )
            Spacer(modifier = Modifier.padding(16.dp))
            EmailField(uiState) { viewModel.onDataChange(it) }
            Spacer(modifier = Modifier.padding(4.dp))
            PasswordField(uiState) { viewModel.onDataChange(it) }
            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                text = stringResource(id = R.string.forgot_password),
                modifier = Modifier
                    .clickable { }
                    .align(Alignment.End),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF3F51B5)
            )
            Spacer(modifier = Modifier.padding(16.dp))
            LoginButton(uiState) {
                coroutineScope.launch {
                    viewModel.onLoginSelected()
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun EmailField(
    status: MainViewModel.LoginUIState,
    onValueChange: (MainViewModel.LoginUIState) -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        value = status.email,
        onValueChange = { onValueChange(status.copy(email = it)) },
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester),
        placeholder = { Text("email") },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = { focusManager.clearFocus() }
        ),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFF2196F3),
            backgroundColor = Color(0xFFA8C1D5)
        )
    )

//    LaunchedEffect(Unit) {
//        focusRequester.requestFocus()
//    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PasswordField(
    uiState: MainViewModel.LoginUIState,
    onValueChange: (MainViewModel.LoginUIState) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    TextField(
        value = uiState.password,
        onValueChange = { onValueChange(uiState.copy(password = it)) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text("password") },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = { keyboardController?.hide() }
        ),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFF2196F3),
            backgroundColor = Color(0xFFA8C1D5)
        )
    )
}

@Composable
fun LoginButton(status: MainViewModel.LoginUIState, onLoginSelected: () -> Unit) {
    Button(
        onClick = { onLoginSelected() },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFFFF5722),
            disabledBackgroundColor = Color(0xFFFF9800),
            contentColor = Color.White,
            disabledContentColor = Color.White
        ),
        enabled = status.isButtonEnabled
    ) {
        Text("Iniciar sesión")
    }
}
