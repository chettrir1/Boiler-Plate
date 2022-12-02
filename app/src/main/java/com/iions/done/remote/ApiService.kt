package com.iions.done.remote

import com.iions.done.feature.auth.data.model.LoginResponse
import com.iions.done.remote.helper.BaseResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {

    @Headers("Accept: application/json")
    @POST("login/phone")
    suspend fun loginWithPhone(
        @Body requestParams: MutableMap<String, Any>
    ): BaseResponse<LoginResponse>
}