package com.rozworks.features.presentation

sealed class HomepageIntent {
    data object GetHomepageData : HomepageIntent()
    data class SomethingClicked(val id: String) : HomepageIntent()
}
