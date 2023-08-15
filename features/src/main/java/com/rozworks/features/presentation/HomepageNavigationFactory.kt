package com.rozworks.features.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.rozworks.core.navigation.NavigationDestination.Homepage
import com.rozworks.core.navigation.NavigationFactory
import com.rozworks.features.presentation.composable.HomepageRoute
import javax.inject.Inject

class HomepageNavigationFactory @Inject constructor() : NavigationFactory {
    override fun create(builder: NavGraphBuilder) {
        builder.composable(Homepage.route) {
            HomepageRoute()
        }
    }
}
