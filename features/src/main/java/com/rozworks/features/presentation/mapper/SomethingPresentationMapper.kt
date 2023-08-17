package com.rozworks.features.presentation.mapper

import com.rozworks.features.domain.model.SomethingDomainObject
import com.rozworks.features.presentation.model.SomethingPresentationObject

fun SomethingDomainObject.toPresentationModel() = SomethingPresentationObject(
    id = id,
    name = name
)