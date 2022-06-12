package com.iions.done.feature.rating.data

import com.iions.done.feature.rating.data.model.PostRatingResponse

interface RatingRepository {
    fun isUserLoggedIn(): Boolean
    suspend fun postRating(itemId: Int, rating: Int, comment: String): PostRatingResponse?

    interface Local {
        fun isUserLoggedIn(): Boolean
        fun getAuthorizationToken(): String
    }

    interface Remote {
        suspend fun postRating(
            authorizationToken: String,
            itemId: Int,
            rating: Int,
            comment: String
        ): PostRatingResponse?
    }
}