package com.lockermanwxlf.openauth.composables

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
actual fun FileDialog(
    onCloseRequest: (result: String?
) -> Unit) {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { result ->
        onCloseRequest(result?.toString())
    }
    LaunchedEffect(Unit) {
        launcher.launch(arrayOf("image/*"))
    }
}