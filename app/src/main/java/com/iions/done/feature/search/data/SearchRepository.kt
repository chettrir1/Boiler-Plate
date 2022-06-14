package com.iions.done.feature.search.data

import com.iions.done.feature.search.data.model.SearchBaseResponse

interface SearchRepository {
    suspend fun requestSearch(query: String? = ""): SearchBaseResponse?

    interface Local {
    }

    interface Remote {
        suspend fun requestSearch(query: String? = ""): SearchBaseResponse?
    }
}