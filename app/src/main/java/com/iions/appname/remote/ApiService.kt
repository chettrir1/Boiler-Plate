package com.iions.appname.remote

import com.iions.appname.feature.auth.data.model.LoginBaseResponse
import com.iions.appname.remote.helper.BaseResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("auth/login")
    suspend fun authenticateUser(
        @Body requestParams: MutableMap<String, Any>
    ): BaseResponse<LoginBaseResponse>

}