package com.iions.appname.utils

import com.iions.appname.exceptions.FailedResponseException
import com.iions.appname.remote.helper.BaseResponse
import io.reactivex.Single
import io.reactivex.annotations.NonNull


class NotNullMapper<T> : io.reactivex.functions.Function<BaseResponse<T>, Single<T>> {
    @Throws(Exception::class)
    override fun apply(@NonNull baseResponse: BaseResponse<T>): Single<T> {

        return if (baseResponse.status!!) {
            val item = baseResponse.response
            if (item == null)
                Single.error(FailedResponseException(-1, baseResponse.statusMessage.toString()))
            else
                Single.just(item)
        } else {
            Single.error(FailedResponseException(-1, "SERVER ERROR"))
        }
    }
}

inline fun <reified T> notNullMapper(baseResponse: BaseResponse<T>): T {
    return if (baseResponse.status!!) {
        val item = baseResponse.response
        item?.let {
            return@let it
        }.orElse {
            throw FailedResponseException(-1, baseResponse.statusMessage.toString())
        }
    } else {
        throw FailedResponseException(-1, "Server Error!")
    }
}