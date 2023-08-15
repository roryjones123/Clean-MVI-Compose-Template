package com.rozworks.features.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class SomethingResponseObject(
    val id: String,
    val name: String,
    val extraUnwantedValue: String,
)
