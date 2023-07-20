package com.iions.done.feature.profile.data

interface ProfileRepository {
    fun getProfile();

    interface Local {
        fun getToken(): String

    }

    interface Remote {
        fun getProfile(token: String);

    }
}