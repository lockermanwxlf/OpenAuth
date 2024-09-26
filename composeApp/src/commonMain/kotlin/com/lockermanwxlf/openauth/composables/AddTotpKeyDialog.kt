package com.lockermanwxlf.openauth.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.lockermanwxlf.openauth.viewmodels.AddTotpKeyViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun AddTotpKeyDialog(
    onDismissRequest: () -> Unit
) {
    val vm = koinViewModel<AddTotpKeyViewModel>()
    val ui = vm.uiState

    var showFilePicker by remember { mutableStateOf(false) }
    if (showFilePicker) {
        FileDialog {
            vm.autofillFromQR(it.toString())
        }
    }

    Dialog(
        onDismissRequest = onDismissRequest
    ) {
        Surface(
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.padding(10.dp)
            ) {
                Text("Add New Key")

                TextField(
                    value = ui.keyBase32,
                    onValueChange = { vm.setKeyBase32(it) },
                    label = { Text("Key in Base32") }
                )

                TextField(
                    value = ui.label,
                    onValueChange = { vm.setLabel(it) },
                    label = { Text("Label") }
                )

                TextField(
                    value = ui.digits,
                    onValueChange = { vm.setDigits(it) },
                    label = { Text("Digits") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                TextField(
                    value = ui.period,
                    onValueChange = { vm.setPeriod(it) },
                    label = { Text("Period") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                TextField(
                    value = ui.algorithm,
                    onValueChange = { vm.setAlgorithm(it) },
                    label = { Text("Algorithm") }
                )

                TextButton(
                    onClick = {
                        showFilePicker = true
                    }
                ) {
                    Text("Autofill from QR Code")
                }

                Button(
                    onClick = {
                        vm.addTotpKey()
                    },
                    enabled = ui.isValid
                ) {
                    Text("Add Key")
                }
            }
        }
    }
}