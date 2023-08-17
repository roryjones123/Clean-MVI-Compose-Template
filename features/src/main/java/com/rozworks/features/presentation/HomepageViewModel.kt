package com.rozworks.features.presentation

import androidx.lifecycle.SavedStateHandle
import com.rozworks.core.BaseViewModel
import com.rozworks.features.domain.usecase.GetHomepageDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject
import com.rozworks.features.presentation.HomepageUiState.PartialState
import com.rozworks.features.presentation.mapper.toPresentationModel

@HiltViewModel
class HomepageViewModel @Inject constructor(
    private val getHomepageDataUseCase: GetHomepageDataUseCase,
    savedStateHandle: SavedStateHandle,
    coinsInitialState: HomepageUiState,
) : BaseViewModel<HomepageUiState, PartialState, HomepageIntent>(
    savedStateHandle,
    coinsInitialState,
) {
    init {
        observeContinuousChanges(getHomePageData())
    }

    override fun mapIntents(intent: HomepageIntent): Flow<PartialState> = when (intent) {
        is HomepageIntent.GetHomepageData -> getHomePageData()
    }

    override fun reduceUiState(
        previousState: HomepageUiState,
        partialState: PartialState,
    ): HomepageUiState = when (partialState) {
        is PartialState.Loading -> previousState.copy(
            isLoading = true,
            isError = false,
        )

        is PartialState.DataFetched -> previousState.copy(
            isLoading = false,
            somethings = partialState.somethings,
            isError = false,
        )

        is PartialState.Error -> previousState.copy(
            isLoading = false,
            isError = true,
        )
    }

    private fun getHomePageData(): Flow<PartialState> =
        getHomepageDataUseCase().map { response ->
            response.fold(
                onSuccess = { somethings ->
                    PartialState.DataFetched(somethings.map { it.toPresentationModel() })
                },
                onFailure = {
                    PartialState.Error(it)
                }
            )
        }.onStart {
            emit(PartialState.Loading)
        }
}