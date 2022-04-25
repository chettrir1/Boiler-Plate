package com.iions.done.remote

import com.iions.done.feature.appointment.screens.AppointmentRemoteBaseResponse
import com.iions.done.feature.auth.data.model.LoginResponse
import com.iions.done.feature.auth.data.model.LogoutResponse
import com.iions.done.feature.auth.data.model.VerifyPinResponse
import com.iions.done.feature.groceries.data.model.AddToCartResponse
import com.iions.done.feature.groceries.data.model.GroceryDetailRemoteBaseResponse
import com.iions.done.feature.groceries.data.model.GroceryRemoteBaseResponse
import com.iions.done.feature.main.data.model.*
import com.iions.done.feature.restaurants.data.model.RestaurantDetailRemoteBaseResponse
import com.iions.done.feature.restaurants.data.model.RestaurantRemoteBaseResponse
import com.iions.done.feature.summary.data.model.CreateOrderBaseResponse
import com.iions.done.remote.helper.BaseResponse
import retrofit2.http.*

interface ApiService {

    @Headers("Accept: application/json")
    @GET("application/home")
    suspend fun getHome(): BaseResponse<HomeResponse>

    @Headers("Accept: application/json")
    @POST("login/phone")
    suspend fun loginWithPhone(
        @Body requestParams: MutableMap<String, Any>
    ): BaseResponse<LoginResponse>

    @Headers("Accept: application/json")
    @POST("validateOtp")
    suspend fun verifyPin(
        @Body requestParams: MutableMap<String, Any>
    ): BaseResponse<VerifyPinResponse>

    @Headers("Accept: application/json")
    @GET("logout")
    suspend fun logout(
        @Header("Authorization") token: String
    ): BaseResponse<LogoutResponse>

    @Headers("Accept: application/json")
    @GET("grocery/items")
    suspend fun getGroceries(@Query("page") page: Int): BaseResponse<GroceryRemoteBaseResponse>

    @Headers("Accept: application/json")
    @POST("grocery/item")
    suspend fun getGroceryDetail(
        @Body requestParams: MutableMap<String, Any>
    ): BaseResponse<GroceryDetailRemoteBaseResponse>

    @Headers("Accept: application/json")
    @POST("cart/addToCart")
    suspend fun requestAddToCart(
        @Header("Authorization") token: String,
        @Body requestParams: MutableMap<String, Any>
    ): BaseResponse<AddToCartResponse>

    @Headers("Accept: application/json")
    @GET("cart/getCart")
    suspend fun getCart(
        @Header("Authorization") token: String
    ): BaseResponse<CartBaseResponse>

    @Headers("Accept: application/json")
    @POST("cart/removeFromCart")
    suspend fun removeFromCart(
        @Header("Authorization") token: String,
        @Body requestParams: MutableMap<String, Any>
    ): BaseResponse<RemoveCartResponse>

    @Headers("Accept: application/json")
    @POST("cart/createOrder")
    suspend fun createOrder(
        @Header("Authorization") token: String,
        @Body requestParams: MutableMap<String, Any>
    ): BaseResponse<CreateOrderBaseResponse>

    @Headers("Accept: application/json")
    @GET("cart/orders")
    suspend fun getOrders(
        @Header("Authorization") token: String
    ): BaseResponse<OrdersBaseResponse>

    @Headers("Accept: application/json")
    @GET("me")
    suspend fun fetchProfile(
        @Header("Authorization") token: String
    ): BaseResponse<ProfileBaseResponse>

    @Headers("Accept: application/json")
    @GET("restaurant/list")
    suspend fun getRestaurants(@Query("page") page: Int): BaseResponse<RestaurantRemoteBaseResponse>

    @Headers("Accept: application/json")
    @POST("restaurant/show")
    suspend fun getRestaurantDetail(
        @Body requestParams: MutableMap<String, Any>
    ): BaseResponse<RestaurantDetailRemoteBaseResponse>

    @Headers("Accept: application/json")
    @POST("me")
    suspend fun editProfile(
        @Header("Authorization") token: String,
        @Body requestParams: MutableMap<String, Any>
    ): BaseResponse<EditProfileResponse>

    @Headers("Accept: application/json")
    @GET("appointment/list")
    fun getAppointment(@Query("page") page: Int): BaseResponse<AppointmentRemoteBaseResponse>

}