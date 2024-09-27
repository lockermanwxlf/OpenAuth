package com.lockermanwxlf.openauth.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import com.lockermanwxlf.openauth.persistence.TotpKey
import com.lockermanwxlf.otp.Totp
import kotlinx.coroutines.delay
import openauth.composeapp.generated.resources.Res
import openauth.composeapp.generated.resources.baseline_content_copy_24
import org.jetbrains.compose.resources.painterResource

@Composable
fun TotpKeyListItem(totpKey: TotpKey) {
    val clipboardManager = LocalClipboardManager.current
    var showCode by rememberSaveable { mutableStateOf(false) }

    var totpCode by rememberSaveable { mutableStateOf("") }
    var timeRemaining by rememberSaveable { mutableFloatStateOf(0F) }

    fun getCounter(): Long {
        return (System.currentTimeMillis() / 1000) / totpKey.period
    }
    var lastCounter by rememberSaveable{ mutableLongStateOf(-1L) }
    LaunchedEffect(Unit) {
        while(true) {
            val counter = getCounter()
            val time = System.currentTimeMillis()
            if (lastCounter != counter) {
                lastCounter = counter
                totpCode = Totp.generateTotp(
                    key = totpKey.key,
                    time = time / 1000,
                    period = totpKey.period,
                    digits = totpKey.digits,
                    algorithm = totpKey.algorithm
                ).toString().padStart(totpKey.digits, '0')
            }
            timeRemaining = (totpKey.period - (time.toDouble() / 1000) % 30).toFloat()
            delay(50L)
        }
    }

    Surface(
        modifier = Modifier
            .padding(5.dp)
            .clickable {
                showCode = !showCode
            },
        elevation = 10.dp,
        shape = RoundedCornerShape(5.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.padding(10.dp)
            ) {
                if (showCode) {
                    Text(totpCode)
                } else {
                    Text(totpKey.label)
                }
                LinearProgressIndicator(
                    progress = timeRemaining.toFloat() / totpKey.period,
                    modifier = Modifier.align(Alignment.BottomCenter)
                )
            }
            IconButton(
                onClick = {
                    clipboardManager.setText(buildAnnotatedString {
                        append(totpCode)
                    })
                }
            ) {
                Icon(painter = painterResource(Res.drawable.baseline_content_copy_24), "Copy code")
            }
        }

    }
}