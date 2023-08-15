package com.rozworks.features.data.mapper

import com.rozworks.features.data.remote.model.SomethingResponseObject
import com.rozworks.features.domain.model.SomethingDomainObject

fun SomethingResponseObject.toDomainModel() = SomethingDomainObject(
    id = id,
    name = name
)