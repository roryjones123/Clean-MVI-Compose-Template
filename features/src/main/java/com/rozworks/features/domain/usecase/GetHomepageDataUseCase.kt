package com.rozworks.features.domain.usecase

import com.rozworks.features.domain.model.SomethingDomainObject
import com.rozworks.features.domain.repository.SomethingRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.retryWhen
import java.io.IOException

private const val RETRY_TIME_IN_MILLIS = 15000L

fun interface GetHomepageDataUseCase : () -> Flow<Result<List<SomethingDomainObject>>>

fun getHomepageData(
    somethingRepository: SomethingRepository,
): Flow<Result<List<SomethingDomainObject>>> = somethingRepository
    .getSomethings()
    .map { Result.success(it) }
    .retryWhen { cause, attempt ->
        if (cause is IOException) {
            delay(RETRY_TIME_IN_MILLIS)
            true
        } else {
            false
        }
    }

