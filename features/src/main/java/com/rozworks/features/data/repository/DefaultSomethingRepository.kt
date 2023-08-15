package com.rozworks.features.data.repository

import com.rozworks.features.data.mapper.toDomainModel
import com.rozworks.features.data.remote.api.SomethingApi
import com.rozworks.features.domain.model.SomethingDomainObject
import com.rozworks.features.domain.repository.SomethingRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DefaultSomethingRepository @Inject constructor(
    private val somethingApi: SomethingApi
) : SomethingRepository {
    override fun getSomethings(): Flow<List<SomethingDomainObject>> =
        flow { emit(somethingApi.getSomethings().map { it.toDomainModel() }) }
}
