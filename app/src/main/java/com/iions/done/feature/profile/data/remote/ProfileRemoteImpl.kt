package com.iions.done.feature.profile.data.remote

import com.iions.done.feature.profile.data.ProfileRepository
import com.iions.done.remote.ApiService
import javax.inject.Inject

class ProfileRemoteImpl @Inject constructor(
    private val apiService: ApiService
) :ProfileRepository.Remote {
    override fun getProfile(token: String) {
        return apiService.getProfile(token);
    }
}