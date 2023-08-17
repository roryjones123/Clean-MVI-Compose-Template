package com.rozworks.features.presentation.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.rozworks.features.presentation.HomepageIntent.GetHomepageData
import com.rozworks.features.presentation.HomepageIntent
import com.rozworks.features.presentation.HomepageUiState

@Composable
fun HomepageRoute(
    viewModel: HomepageViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomepageScreen(
        uiState = uiState,
        onIntent = viewModel::acceptIntent,
    )
}

@Composable
internal fun HomepageScreen(
    uiState: HomepageUiState,
    onIntent: (HomepageIntent) -> Unit,
) {
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            Button(onClick = { onIntent(GetHomepageData) })
            {
                Icon(
                    imageVector = Icons.Outlined.Refresh,
                    contentDescription = "Refresh Icon"
                )
            }
        },
        content = { padding ->
            Column(modifier = Modifier.padding(padding)) {
                when {
                    uiState.isLoading -> {
                        HomepageLoadingContent()
                    }
                    uiState.somethings.isNotEmpty() -> {
                        HomepageListContent(
                            somethingList = uiState.somethings
                        )
                    }
                    uiState.isError -> {
                        HomepageErrorContent()
                    }
                }
            }
        })
}