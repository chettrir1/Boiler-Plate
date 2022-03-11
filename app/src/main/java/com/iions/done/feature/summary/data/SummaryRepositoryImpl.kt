package com.iions.done.feature.summary.data

import com.iions.done.feature.main.data.model.CartBaseResponse
import com.iions.done.utils.SchedulersFactory
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SummaryRepositoryImpl @Inject constructor(
    private val schedulersFactory: SchedulersFactory,
    private val localRepository: SummaryRepository.Local,
    private val remoteRepository: SummaryRepository.Remote
) : SummaryRepository {

    override suspend fun fetchCartList(): CartBaseResponse? {
        return withContext(schedulersFactory.io()) {
            remoteRepository.fetchCartList(localRepository.getAuthorizationToken())
        }
    }

}
