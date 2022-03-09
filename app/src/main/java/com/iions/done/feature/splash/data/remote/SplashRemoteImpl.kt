package com.iions.done.feature.splash.data.remote

import com.iions.done.feature.main.data.model.HomeResponse
import com.iions.done.feature.splash.data.SplashRepository
import com.iions.done.remote.ApiService
import com.iions.done.utils.notNullMapper
import javax.inject.Inject

class SplashRemoteImpl @Inject constructor(
    private val apiService: ApiService
) : SplashRepository.Remote {

    override suspend fun fetchHomeResponse(): HomeResponse {
        return notNullMapper(apiService.getHome())
    }
}