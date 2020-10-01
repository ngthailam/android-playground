package vn.thailam.challenge1.core.utils

import java.text.DecimalFormat

fun Long?.isNullOrZero(): Boolean {
    return this == null || this == 0L
}

fun Long?.orZero(): Long {
    return this ?: 0
}

fun String.toFormattedUsd(): String {
    return "$${toFormattedMoney()}"
}

// TODO: support decimal places and multiple country money
fun String.toFormattedMoney(): String {
    val formatter = DecimalFormat("###,###,##0.00")
    val amount = this.removeNonDigits().toDoubleOrNull() ?: return ""
    return formatter.format(amount)
}

fun String.removeNonDigits(): String {
    val re = Regex("[^A-Za-z0-9 ]")
    return re.replace(this, "")
}