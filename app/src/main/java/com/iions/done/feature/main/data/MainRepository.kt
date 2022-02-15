package com.iions.done.feature.main.data

interface MainRepository {
    fun isUserLoggedIn(): Boolean

    interface Local {
        fun isUserLoggedIn(): Boolean
    }

    interface Remote {
    }
}