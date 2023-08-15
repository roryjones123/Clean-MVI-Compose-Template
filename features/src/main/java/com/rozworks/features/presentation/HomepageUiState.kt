package com.rozworks.features.presentation

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.roz.coinfetcher.basicfeature.presentation.model.CoinDisplayable
import com.roz.coinfetcher.basicfeature.presentation.model.TagDisplayable
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class HomepageUiState(
    val isLoading: Boolean = false,
    val coins: List<CoinDisplayable> = emptyList(),
    val tags: List<TagDisplayable> = emptyList(),
    val isError: Boolean = false,
    val complexCoin: String? = null
) : Parcelable {

    sealed class PartialState {
        data object Loading : PartialState()

        data class Fetched(val coinList: List<CoinDisplayable>, val tagList: List<TagDisplayable>) : PartialState()

        data class Error(val throwable: Throwable) : PartialState()

        data class Filter(
            val tagPressed: TagDisplayable,
            val filteredCoins: List<CoinDisplayable>,
            val filteredTags: List<TagDisplayable>
        ) : PartialState()

        data class Dialog(val complexCoin: String?) : PartialState()
    }
}
