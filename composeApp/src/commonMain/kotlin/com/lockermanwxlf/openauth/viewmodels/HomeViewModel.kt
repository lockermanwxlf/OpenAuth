package com.lockermanwxlf.openauth.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lockermanwxlf.openauth.persistence.TotpKey
import com.lockermanwxlf.openauth.repositories.TotpKeyRepository
import kotlinx.coroutines.launch
import org.apache.commons.codec.binary.Base32

class HomeViewModel(
    private val totpKeyRepository: TotpKeyRepository
): ViewModel() {
    val totpKeysFlow = totpKeyRepository.getTotpKeysFlow()

    fun addTotpKey(
        keyBase32: String,
        digits: Int,
        period: Int,
        label: String,
        algorithm: String
    ) = viewModelScope.launch {
        totpKeyRepository.addTotpKey(TotpKey(
            key = Base32.builder().get().decode(keyBase32),
            digits = digits,
            period = period,
            label = label,
            algorithm = algorithm
        ))
    }
}