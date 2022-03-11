package com.iions.done.feature.summary.data

import com.iions.done.feature.main.data.model.CartBaseResponse

interface SummaryRepository {
    suspend fun fetchCartList(): CartBaseResponse?

    interface Local {
        fun getAuthorizationToken(): String
    }

    interface Remote {
        suspend fun fetchCartList(authorizationToken: String): CartBaseResponse?
    }
}