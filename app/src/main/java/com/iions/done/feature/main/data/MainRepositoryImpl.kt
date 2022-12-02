package com.iions.done.feature.main.data

import com.iions.done.utils.SchedulersFactory
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val schedulersFactory: SchedulersFactory,
    private val localRepository: MainRepository.Local,
    private val remoteRepository: MainRepository.Remote
) : MainRepository {

}
