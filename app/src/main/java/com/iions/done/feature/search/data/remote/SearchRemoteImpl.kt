package com.iions.done.feature.search.data.remote

import com.iions.done.exceptions.FailedResponseException
import com.iions.done.feature.search.data.SearchRepository
import com.iions.done.feature.search.data.model.SearchBaseResponse
import com.iions.done.remote.ApiService
import javax.inject.Inject

class SearchRemoteImpl @Inject constructor(
    private val apiService: ApiService
) : SearchRepository.Remote {
    override suspend fun requestSearch(query: String?): SearchBaseResponse? {
        val remoteResponse = apiService.requestSearch(query ?: "")
        if (remoteResponse.status == true) {
            throw FailedResponseException(
                remoteResponse.status!!,
                remoteResponse.message.toString()
            )
        } else {
            return remoteResponse.response
        }
    }
}