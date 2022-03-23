package com.iions.done.remote

object Constants {
    const val READ_TIME_OUT: Long = 40
    const val CONNECT_TIME_OUT: Long = 40
    const val TOKEN_EXPIRED = 403
    const val AUTHENTICATION_ERROR_401 = 401
    const val INTERNAL_SERVER_ERROR = 500
    const val GATE_WAY_TIME_OUT = 504
    const val VALIDATION_ERROR = 422
    const val FILE_NOT_FOUND = 404

    const val ORDER_RESPONSE = "order"
    const val ORDER_DRAFT = "Draft"
    const val ORDER_SUCCESS = "Success"
    const val ORDER_CANCELED = "Canceled"
    const val ORDER_FAILED = "Failed"
}