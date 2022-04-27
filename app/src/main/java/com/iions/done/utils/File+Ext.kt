package com.iions.done.utils

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

fun File.toMultipartBodyPart(name: String): MultipartBody.Part =
    MultipartBody.Part.createFormData(
        name,
        this.name,
        this.asRequestBody("image/*".toMediaTypeOrNull())
    )