package com.lockermanwxlf.openauth.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lockermanwxlf.openauth.composables.TotpKeyListItem
import com.lockermanwxlf.openauth.viewmodels.HomeViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun HomeScreen() {
    val vm = koinViewModel<HomeViewModel>()
    val totpKeys by vm.totpKeysFlow.collectAsState(listOf())
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Button(onClick = {
                vm.addTotpKey(
                    keyBase32 = "I65VU7K5ZQL7WB4E",
                    digits = 6,
                    period = 30,
                    label = "authenticationtest.com",
                    algorithm = "Sha1"
                )
            }) {
                Text("Add sample key")
            }
            LazyColumn(
                modifier = Modifier.padding(20.dp)
            ) {
                items(totpKeys) {
                    TotpKeyListItem(it)
                }
            }
        }
    }

}