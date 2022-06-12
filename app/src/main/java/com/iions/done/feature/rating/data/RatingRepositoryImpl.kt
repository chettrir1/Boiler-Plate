package com.iions.done.feature.rating.data

import com.iions.done.feature.rating.data.model.PostRatingResponse
import com.iions.done.utils.SchedulersFactory
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RatingRepositoryImpl @Inject constructor(
    private val schedulersFactory: SchedulersFactory,
    private val localRepository: RatingRepository.Local,
    private val remoteRepository: RatingRepository.Remote
) : RatingRepository {

    override fun isUserLoggedIn(): Boolean {
        return localRepository.isUserLoggedIn()
    }

    override suspend fun postRating(
        itemId: Int,
        rating: Int,
        comment: String
    ): PostRatingResponse? {
        return withContext(schedulersFactory.io()) {
            remoteRepository.postRating(
                localRepository.getAuthorizationToken(),
                itemId,
                rating,
                comment
            )
        }
    }
}
