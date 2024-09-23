package com.lockermanwxlf.openauth.composables

import androidx.compose.runtime.Composable

@Composable
expect fun FileDialog(
    onCloseRequest: (result: String?) -> Unit
)