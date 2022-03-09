package com.iions.done.feature.summary.data.remote

import com.iions.done.feature.summary.data.SummaryRepository
import com.iions.done.remote.ApiService
import javax.inject.Inject

class SummaryRemoteImpl @Inject constructor(
    private val apiService: ApiService
) : SummaryRepository.Remote {
}