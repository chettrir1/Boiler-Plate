package com.iions.done.remote

import com.iions.done.feature.auth.data.model.LoginBaseResponse
import com.iions.done.remote.helper.BaseResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("auth/login")
    suspend fun authenticateUser(
        @Body requestParams: MutableMap<String, Any>
    ): BaseResponse<LoginBaseResponse>

}