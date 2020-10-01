package vn.thailam.challenge1.features.list.models

import androidx.annotation.DrawableRes

data class ChequeListItemUiModel(
    val id: Long,
    val title: String = "",
    val leadingIconUrl: String = "",
    val displayAmount: String = "",
    @DrawableRes
    val statusIconRes: Int? = null,
    val displayDate: String = "",
    val displayStatus: String = ""
)