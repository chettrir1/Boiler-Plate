package com.iions.done.utils.archcomponents

/**
 * Used to handle Response from the Network layer or Database layer
 * @param status maintain current state
 * @param data get data from data layer
 * @param error handle exception
 */
data class Response<ResultType>(
    var status: Status,
    var data: ResultType? = null,
    var error: Throwable? = null
) {

    companion object {
        fun <ResultType> loading(message: ResultType? = null): Response<ResultType> {
            return Response(Status.LOADING, message, null)
        }

        fun <ResultType> complete(data: ResultType?): Response<ResultType> {
            return Response(Status.COMPLETE, data, null)
        }

        fun <ResultType> error(e: Throwable?): Response<ResultType> {
            return Response(Status.ERROR, null, e)
        }
    }
}