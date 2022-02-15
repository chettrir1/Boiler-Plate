package com.iions.appname.feature.main.data.remote

import com.iions.appname.feature.main.data.MainRepository
import com.iions.appname.remote.ApiService
import javax.inject.Inject

class MainRemoteImpl @Inject constructor(
    private val apiService: ApiService
) : MainRepository.Remote {
}