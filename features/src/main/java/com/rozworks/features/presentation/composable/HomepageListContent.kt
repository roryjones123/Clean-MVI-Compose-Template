package com.rozworks.features.presentation.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.rozworks.cleanmvicomposetemplate.features.R
import com.rozworks.features.presentation.model.SomethingPresentationObject

@Composable
fun HomepageListContent(
    somethingList: List<SomethingPresentationObject>,
    modifier: Modifier = Modifier
) {
    Column {
        LazyColumn(
            modifier = modifier
                .padding(
                    horizontal = dimensionResource(id = R.dimen.dimen_medium),
                ),
        ) {
            itemsIndexed(
                items = somethingList,
                key = { _, something -> something.id },
            ) { index, item ->
                SomethingItem(
                    something = item
                )
            }
        }
    }
}