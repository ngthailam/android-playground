package vn.thailam.challenge1.features.detail.models

import androidx.annotation.DrawableRes

data class ChequeDetailUiModel(
    val id: Long,
    val title: String,
    val displayAmount: String,
    val displayStatus: String,
    @DrawableRes
    val statusIconRes: Int?,
    val leadingIconUrl: String,
    val displayDate: String,
    val items: List<ChequeItemUiModel> = emptyList()
)
