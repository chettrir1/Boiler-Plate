package com.iions.done.feature.splash.data

import com.iions.done.feature.main.data.model.HomeResponse
import com.iions.done.utils.SchedulersFactory
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SplashRepositoryImpl @Inject constructor(
    private val schedulersFactory: SchedulersFactory,
    private val localRepository: SplashRepository.Local,
    private val remoteRepository: SplashRepository.Remote
) : SplashRepository {

    override suspend fun fetchHomeResponse(): HomeResponse? {
        return withContext(schedulersFactory.io()) {
            val response = remoteRepository.fetchHomeResponse()
            localRepository.fetchHomeResponse(response)
            response
        }
    }
}
