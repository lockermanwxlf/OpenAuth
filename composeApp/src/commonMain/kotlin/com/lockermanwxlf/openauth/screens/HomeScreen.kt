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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lockermanwxlf.openauth.composables.AddTotpKeyDialog
import com.lockermanwxlf.openauth.composables.TotpKeyListItem
import com.lockermanwxlf.openauth.viewmodels.HomeViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun HomeScreen() {
    val vm = koinViewModel<HomeViewModel>()
    val totpKeys by vm.totpKeysFlow.collectAsState(listOf())

    var showAddTotpKeyDialog by rememberSaveable { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Button(onClick = {
                showAddTotpKeyDialog = true
            }) {
                Text("Add key")
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

    if (showAddTotpKeyDialog) {
        AddTotpKeyDialog(
            onDismissRequest = {
                showAddTotpKeyDialog = false
            }
        )
    }

}