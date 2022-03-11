package com.iions.done.utils

import com.iions.done.exceptions.FailedResponseException
import com.iions.done.remote.helper.BaseResponse
import io.reactivex.Single
import io.reactivex.annotations.NonNull

inline fun <reified T> notNullMapper(baseResponse: BaseResponse<T>): T {
    return if (baseResponse.status == false) {
        val item = baseResponse.response
        item?.let {
            return@let it
        }.orElse {
            throw FailedResponseException(baseResponse.status!!, baseResponse.message.toString())
        }
    } else {
        throw FailedResponseException(baseResponse.status!!, baseResponse.message.toString())
    }
}