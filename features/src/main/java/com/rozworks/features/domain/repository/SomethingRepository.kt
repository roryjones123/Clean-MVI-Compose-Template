package com.rozworks.features.domain.repository

import com.rozworks.features.domain.model.SomethingDomainObject
import kotlinx.coroutines.flow.Flow

interface SomethingRepository {
    fun getSomethings(): Flow<List<SomethingDomainObject>>
}
