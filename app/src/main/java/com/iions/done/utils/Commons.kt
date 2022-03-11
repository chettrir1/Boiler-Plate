package com.iions.done.utils

import java.text.DecimalFormat

object Commons {
    fun currencyFormatter(value: Int): String {
        val formatter = DecimalFormat("#,###,###")
        val formatedValue = (formatter.format(value)).replace("(?<=^\\d+)\\0.*$", "")
        return if (formatedValue.contains("."))
            formatedValue.replace(".", ",")
        else
            formatedValue
    }

}