package es.codekai.composer.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import es.codekai.composer.MainViewModel
import es.codekai.composer.R

@Composable
fun LoginScreen(viewModel: MainViewModel) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Login(Modifier.align(Alignment.Center), viewModel)
    }
}

@Composable
fun Login(modifier: Modifier, viewModel: MainViewModel) {
    val email: String by viewModel.email.observeAsState(initial = "")
    Column(modifier = modifier) {
        Image(
            modifier = modifier,
            painter = painterResource(id = R.drawable.clint2),
            contentDescription = "header"
        )
        Spacer(modifier = Modifier.padding(16.dp))
        EmailField(email) { viewModel.onChangeEmail(it) }
        Spacer(modifier = Modifier.padding(4.dp))
        PasswordField()
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
        LoginButton()
    }
}

@Composable
fun EmailField(email: String, onValueChange: (String) -> Unit) {
    TextField(
        value = email,
        onValueChange = { onValueChange(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text("email") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFF2196F3),
            backgroundColor = Color(0xFFA8C1D5)
        )
    )
}

@Composable
fun PasswordField() {
    TextField(
        value = "",
        onValueChange = {},
        modifier = Modifier.fillMaxWidth(),
        placeholder = { Text("password") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color(0xFF2196F3),
            backgroundColor = Color(0xFFA8C1D5)
        )
    )
}

@Composable
fun LoginButton() {
    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFFFF5722),
            disabledBackgroundColor = Color(0xFFFF9800),
            contentColor = Color.White,
            disabledContentColor = Color.White
        )
    ) {
        Text("Iniciar sesión")
    }
}
