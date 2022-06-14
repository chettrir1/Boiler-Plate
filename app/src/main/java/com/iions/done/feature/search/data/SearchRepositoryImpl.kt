package com.iions.done.feature.search.data

import com.iions.done.feature.search.data.model.SearchBaseResponse
import com.iions.done.utils.SchedulersFactory
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val schedulersFactory: SchedulersFactory,
    private val localRepository: SearchRepository.Local,
    private val remoteRepository: SearchRepository.Remote
) : SearchRepository {

    override suspend fun requestSearch(query: String?): SearchBaseResponse? {
        return withContext(schedulersFactory.io()) {
            remoteRepository.requestSearch(query)
        }
    }

}
