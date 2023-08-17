package com.rozworks.features.presentation.mapper

import com.rozworks.features.presentation.model.SomethingPresentationObject

fun SomethingPresentationObject.toPresentationModel() = SomethingPresentationObject(
    id = id,
    name = name
)