package com.iions.done.feature.profile.data

import com.iions.done.utils.SchedulersFactory
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val schedulersFactory: SchedulersFactory,
    private val localRepository: ProfileRepository.Local,
    private val remoteRepository: ProfileRepository.Remote
) : ProfileRepository {

    override fun getProfile() {
        var token = localRepository.getToken()
        remoteRepository.getProfile(token)
    }
}