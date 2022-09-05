package es.codekai.composer

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    data class LoginUIState(
        val email: String = "",
        val password: String = "",
        val isButtonEnabled: Boolean = false,
        val isLoading: Boolean = false
    )

    private val _loginUIState = MutableLiveData<LoginUIState>()
    val loginUIState: LiveData<LoginUIState> = _loginUIState

    suspend fun onLoginSelected() {
//    fun onLoginSelected() {
//        viewModelScope.launch {
//            val state = _loginUIState.value
//            _loginUIState.value = state?.copy(isLoading = true, password = "", email = "")
//            delay(3000)
//            val state2 = _loginUIState.value
//            _loginUIState.value = state2?.copy(isLoading = false)
//        }

        var state = _loginUIState.value
        _loginUIState.value = state?.copy(isLoading = true, password = "", email = "", isButtonEnabled = false)
        delay(3000)
        state = _loginUIState.value
        _loginUIState.value = state?.copy(isLoading = false)
    }

    fun onDataChange(loginUIState: LoginUIState) {
        val status = _loginUIState.value
        status?.let { loginUiState ->
            val buttonEnabled =
                isValidEmail(loginUIState.email) && isValidPassword(loginUIState.password)

            _loginUIState.value = loginUiState.copy(
                email = loginUIState.email,
                password = loginUIState.password,
                isButtonEnabled = buttonEnabled
            )
        } ?: run { _loginUIState.value = loginUIState }
    }

    private fun isValidPassword(password: String) = password.length > 1

    private fun isValidEmail(email: String) = Patterns.EMAIL_ADDRESS.matcher(email).matches()
}
