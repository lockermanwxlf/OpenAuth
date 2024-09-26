package com.lockermanwxlf.openauth.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lockermanwxlf.googleauthenticatorspec.GoogleAuthenticatorKey
import com.lockermanwxlf.openauth.ocr.Ocr
import com.lockermanwxlf.openauth.persistence.TotpKey
import com.lockermanwxlf.openauth.repositories.TotpKeyRepository
import com.lockermanwxlf.openauth.util.InputStreamProvider
import kotlinx.coroutines.launch
import org.apache.commons.codec.binary.Base32
import java.net.URI

class AddTotpKeyViewModel(
    private val totpKeyRepository: TotpKeyRepository,
    private val inputStreamProvider: InputStreamProvider
): ViewModel() {
    var uiState by mutableStateOf(AddTotpKeyUiState())
        private set

    private fun getEntityFromUiState(): TotpKey? {
        return TotpKey(
            key = Base32.builder().get().decode(uiState.keyBase32),
            label = uiState.label,
            digits = uiState.digits.toIntOrNull() ?: return null,
            period = uiState.period.toIntOrNull() ?: return null,
            algorithm = uiState.algorithm
        )
    }

    private fun validateForm() {
        val isValid = uiState.keyBase32.isNotEmpty() && uiState.label.isNotEmpty()
        uiState = uiState.copy(isValid = isValid)
    }

    fun addTotpKey() {
        val entity = getEntityFromUiState()
        if (entity != null) {
            viewModelScope.launch {
                totpKeyRepository.addTotpKey(entity)
            }
        }
    }

    fun setKeyBase32(keyBase32: String) {
        uiState = uiState.copy(
            keyBase32 = keyBase32
        )
        validateForm()
    }

    fun setLabel(label: String) {
        uiState = uiState.copy(
            label = label
        )
        validateForm()
    }

    fun setDigits(digits: String) {
        if (digits.toUIntOrNull() != null) {
            uiState = uiState.copy(digits = digits)
        }
    }

    fun setPeriod(period: String) {
        if (period.toUIntOrNull() != null) {
            uiState = uiState.copy(period = period)
        }
    }

    fun setAlgorithm(algorithm: String) {
        uiState = uiState.copy(algorithm = algorithm)
    }

    fun autofillFromQR(path: String) {
        val qrCode = viewModelScope.launch {
            val inputStream = inputStreamProvider.getInputStream(path)
            if (inputStream != null) {
                val qrText = Ocr.readQR(inputStream)
                if (qrText != null) {
                    val keyUri = URI.create(qrText)
                    val entity = GoogleAuthenticatorKey.fromURI(keyUri)
                    uiState = uiState.copy(
                        keyBase32 = entity.secret,
                        label = entity.label.accountName.let { accountName ->
                            entity.label.issuer?.let { issuer ->
                                "$issuer: $accountName"
                            } ?: accountName
                        },
                        digits = (entity.digits ?: 6).toString(),
                        period = (entity.period ?: 30).toString(),
                        algorithm = (entity.algorithm ?: "Sha1").toString()
                    )
                    validateForm()
                }
            }
        }
    }
}

data class AddTotpKeyUiState(
    val keyBase32: String = "",
    val label: String = "",
    val digits: String = "6",
    val period: String = "30",
    val algorithm: String = "Sha1",
    val isValid: Boolean = false
)