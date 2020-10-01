package vn.thailam.challenge1.core.data.models

import vn.thailam.challenge1.core.annotation.ChequeStatus

data class ChequeEntity(
    val id: Long? = null,
    val name: String? = null,
    val amount: String? = null,
    val iconUrl: String? = null,
    @ChequeStatus
    val status: String = ChequeStatus.UNDEFINED,
    val unixDate: Long? = null,
    val items: List<ChequeItemEntity>? = null
)
