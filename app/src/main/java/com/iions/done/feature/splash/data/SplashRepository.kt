package com.iions.done.feature.splash.data

import com.iions.done.feature.main.data.model.HomeResponse

interface SplashRepository {
    suspend fun fetchHomeResponse(): HomeResponse?

    interface Local {
        suspend fun fetchHomeResponse(response: HomeResponse?)
    }

    interface Remote {
        suspend fun fetchHomeResponse(): HomeResponse?
    }
}