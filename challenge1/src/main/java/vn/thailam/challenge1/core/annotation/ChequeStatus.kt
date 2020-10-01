package vn.thailam.challenge1.core.annotation

import androidx.annotation.StringDef
import vn.thailam.challenge1.core.annotation.ChequeStatus.Companion.CHECKED
import vn.thailam.challenge1.core.annotation.ChequeStatus.Companion.COMPLAINT
import vn.thailam.challenge1.core.annotation.ChequeStatus.Companion.PENDING
import vn.thailam.challenge1.core.annotation.ChequeStatus.Companion.UNCHECKED
import vn.thailam.challenge1.core.annotation.ChequeStatus.Companion.UNDEFINED

@StringDef(
    value = [
        UNDEFINED,
        UNCHECKED,
        CHECKED,
        COMPLAINT,
        PENDING
    ]
)
@Retention(AnnotationRetention.SOURCE)
annotation class ChequeStatus {
    companion object {
        const val UNDEFINED = "Undefined"
        const val UNCHECKED = "Unchecked"
        const val CHECKED = "Checked"
        const val COMPLAINT = "Complaint"
        const val PENDING = "Pending"
    }
}
