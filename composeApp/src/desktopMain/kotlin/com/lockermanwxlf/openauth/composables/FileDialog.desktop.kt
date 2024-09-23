package com.lockermanwxlf.openauth.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.AwtWindow
import java.awt.FileDialog
import java.awt.Frame

@Composable
actual fun FileDialog(
    onCloseRequest: (result: String?) -> Unit
) = AwtWindow(
    create = {
        object : FileDialog(null as Frame?, "Pick QR Code image", LOAD) {
            override fun setVisible(b: Boolean) {
                super.setVisible(b)
                if (b) {
                    onCloseRequest(
                        this.files.firstOrNull()?.absolutePath)
                }
            }
        }
    },
    dispose = FileDialog::dispose
)