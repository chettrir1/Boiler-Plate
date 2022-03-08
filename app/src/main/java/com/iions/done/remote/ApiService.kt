package com.iions.done.remote

import com.iions.done.feature.auth.data.model.LoginResponse
import com.iions.done.feature.auth.data.model.LogoutResponse
import com.iions.done.feature.auth.data.model.VerifyPinResponse
import com.iions.done.feature.groceries.data.model.GroceryRemoteBaseResponse
import com.iions.done.feature.main.data.model.HomeResponse
import com.iions.done.remote.helper.BaseResponse
import retrofit2.http.*

interface ApiService {

    @GET("application/home")
    suspend fun getHome(): BaseResponse<HomeResponse>

    @POST("login/phone")
    suspend fun loginWithPhone(
        @Body requestParams: MutableMap<String, Any>
    ): BaseResponse<List<LoginResponse>>

    @POST("validateOtp")
    suspend fun verifyPin(
        @Body requestParams: MutableMap<String, Any>
    ): BaseResponse<VerifyPinResponse>

    @POST("logout")
    suspend fun logout(
        @Header("Authorization") token: String
    ): BaseResponse<LogoutResponse>

    @POST("login/phone")
    suspend fun authenticateUser(
        @Body requestParams: MutableMap<String, Any>
    ): BaseResponse<LoginResponse>

    @GET("grocery/items")
    suspend fun getGroceries(@Query("page") page: Int): BaseResponse<GroceryRemoteBaseResponse>

}