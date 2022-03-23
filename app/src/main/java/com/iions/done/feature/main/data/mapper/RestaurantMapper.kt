package com.iions.done.feature.main.data.mapper

import com.iions.done.feature.main.data.model.HomeRestaurantRemoteResponse
import com.iions.entity.RestaurantEntity

object RestaurantMapper {
    fun mapToLocal(restaurant: List<HomeRestaurantRemoteResponse>): List<RestaurantEntity> {
        return restaurant.map {
            RestaurantEntity(
                restaurantId = it.id ?: -1,
                restaurantName = it.name ?: "",
                restaurantLogo = it.logo ?: "",
                restaurantImage = it.coverImage ?: "",
                restaurantAddress = it.address ?: "",
                restaurantDescription = it.description ?: "",
                restaurantLatitude = it.latitude ?: "",
                restaurantLongitude = it.longitude ?: ""
            )
        }
    }
}