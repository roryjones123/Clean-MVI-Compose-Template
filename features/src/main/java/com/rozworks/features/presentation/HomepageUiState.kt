package com.rozworks.features.presentation

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.rozworks.features.presentation.model.SomethingPresentationObject
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class HomepageUiState(
    val isLoading: Boolean = false,
    val somethings: List<SomethingPresentationObject> = emptyList(),
    val isError: Boolean = false,
) : Parcelable {

    sealed class PartialState {
        data object Loading : PartialState()
        data class Fetched(val coinList: List<SomethingPresentationObject>) : PartialState()
        data class Error(val throwable: Throwable) : PartialState()
    }
}
