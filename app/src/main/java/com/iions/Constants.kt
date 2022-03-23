package com.iions

object Constants {
    const val PREF_FILE = "rosia-sales"
    const val PREF_TOKEN = "access_token"
    const val PREF_REFRESH_TOKEN = "refresh_token"
    const val IS_NEW_USER = "new_user"
    const val DB_NAME = "app.db"
    const val GENERIC_ID = "id"
    const val GENERIC_TITLE = "title"
    const val TYPE_LOGOUT = "logout"

    /*preferences*/
    const val PREF_USER_ID = "userId"
    const val PREF_USER_NAME = "username"
    const val PREF_NAME = "name"
    const val PREF_PHONE = "phone"
    const val PREF_EMAIL = "email"
    const val PREF_LOGIN_DATE = "login_date"

    /*user*/
    const val TBL_USERS = "users"
    const val USER_ID = "id"
    const val USER_FULL_NAME = "full_name"
    const val USER_EMAIL = "email"
    const val USER_PHONE_NUMBER = "phone_number"
    const val USER_FIRST_NAME = "first_name"
    const val USER_LAST_NAME = "last_name"
    const val USER_ROLE_ID = "role_id"
    const val USER_ROLE = "role"
    const val USER_PROFILE_IMAGE = "profile_image"

    const val TBL_USER_ADDRESS = "address"
    const val USER_ADDRESS_ID = "user_address_id"
    const val USER_ADDRESS_DISTRICT_ID = "district_id"
    const val USER_ADDRESS_DISTRICT = "district_name"
    const val USER_ADDRESS_STREET_ID = "street_id"
    const val USER_ADDRESS_STREET = "street_name"
    const val USER_ADDRESS_LOCAL = "local_address"

    /*grocery*/
    const val TBL_GROCERY = "grocery"
    const val GROCERY_ID = "grocery_id"
    const val GROCERY_SKU = "grocery_sku"
    const val GROCERY_NAME = "grocery_name"
    const val BRAND_ID = "grocery_brand_id"
    const val GROCERY_COVER_IMAGE = "grocery_cover_image"
    const val CATEGORY_ID = "grocery_category_id"
    const val GROCERY_HAS_VARIANT = "grocery_has_variant"
    const val GROCERY_PARENT_ID = "grocery_parent_id"

    /*restaurant*/
    const val TBL_RESTAURANT = "restaurant"
    const val RESTAURANT_ID = "restaurant_id"
    const val RESTAURANT_NAME = "restaurant_name"
    const val RESTAURANT_LOGO = "restaurant_logo"
    const val RESTAURANT_IMAGE = "restaurant_cover_image"
    const val RESTAURANT_ADDRESS = "restaurant_address"
    const val RESTAURANT_DESCRIPTION = "restaurant_description"
    const val RESTAURANT_LAT = "restaurant_latitude"
    const val RESTAURANT_LNG = "restaurant_longitude"

    /*category*/
    const val TBL_GROCERY_CATEGORY = "grocery_category"
    const val GROCERY_CATEGORY_ID = "grocery_category_id"
    const val GROCERY_CATEGORY_NAME = "grocery_category_name"

    /*module*/
    const val TBL_MODULE = "module"
    const val MODULES_NAME = "module_name"
    const val MODULES_ICON = "module_icon"

    /*banners*/
    const val TBL_BANNER = "banner"
    const val BANNER_ID = "banner_id"
    const val BANNER_IMAGE = "banner_image"

    /*district*/
    const val TBL_DISTRICT = "district"
    const val DISTRICT_ID = "district_id"
    const val DISTRICT_NAME = "district_name"


    /*street */
    const val TBL_STREET = "street"
    const val STREET_ID = "street_id"
    const val STREET_NAME = "street_name"

    /*restaurant*/
}