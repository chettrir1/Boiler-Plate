package com.iions.done.feature.main.data

import com.iions.done.feature.main.data.model.BannerResponse
import com.iions.done.feature.main.data.model.CategoryResponse
import com.iions.done.utils.SchedulersFactory
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val schedulersFactory: SchedulersFactory,
    private val localRepository: MainRepository.Local,
    private val remoteRepository: MainRepository.Remote
) : MainRepository {
    override fun isUserLoggedIn(): Boolean {
        return localRepository.isUserLoggedIn()
    }

    override suspend fun fetchCategoryList(): List<CategoryResponse>? {
        return withContext(schedulersFactory.io()) {
            localRepository.fetchCategoryList()
        }
    }

    override suspend fun fetchBannerList(): List<BannerResponse>? {
        return withContext(schedulersFactory.io()) {
            localRepository.fetchBannerList()
        }
    }
}
