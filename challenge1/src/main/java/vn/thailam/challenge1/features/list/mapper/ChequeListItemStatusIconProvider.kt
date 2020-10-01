package vn.thailam.challenge1.features.list.mapper

import androidx.annotation.DrawableRes
import vn.thailam.challenge1.R
import vn.thailam.challenge1.core.annotation.ChequeStatus
import vn.thailam.challenge1.core.annotation.ChequeStatus.Companion.CHECKED
import vn.thailam.challenge1.core.annotation.ChequeStatus.Companion.COMPLAINT
import vn.thailam.challenge1.core.annotation.ChequeStatus.Companion.PENDING
import vn.thailam.challenge1.core.annotation.ChequeStatus.Companion.UNCHECKED

class ChequeListItemStatusIconProvider {

    @DrawableRes
    fun provide(@ChequeStatus status: String): Int? {
        return when (status) {
            UNCHECKED -> R.drawable.ic_unchecked_square_24
            CHECKED -> R.drawable.ic_checked_square_24
            COMPLAINT -> R.drawable.ic_warning_24
            PENDING -> R.drawable.ic_pending_24
            else -> null
        }
    }
}