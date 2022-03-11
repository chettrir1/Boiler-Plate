package com.iions.done.exceptions

import java.io.IOException

class FailedResponseException(val status: Boolean, override val message: String) :
    IOException(message)

class NetworkNotAvailableException(errorMessage: String) : IOException(errorMessage)
class DataNotAvailableException : IOException()