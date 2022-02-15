package com.iions.appname.feature.main.data

import com.iions.appname.utils.SchedulersFactory
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val schedulersFactory: SchedulersFactory,
    private val localRepository: MainRepository.Local,
    private val remoteRepository: MainRepository.Remote
) : MainRepository {
    override fun isUserLoggedIn(): Boolean {
        return localRepository.isUserLoggedIn()
    }
}
