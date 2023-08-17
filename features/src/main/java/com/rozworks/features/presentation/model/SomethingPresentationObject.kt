package com.rozworks.features.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class SomethingPresentationObject(
    val id: String,
    val name: String
) : Parcelable