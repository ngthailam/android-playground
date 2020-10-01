package vn.thailam.challenge1.features.list.mapper

import vn.thailam.challenge1.core.data.models.ChequeEntity
import vn.thailam.challenge1.core.utils.DateFormatUtils
import vn.thailam.challenge1.core.utils.isNullOrZero
import vn.thailam.challenge1.core.utils.orZero
import vn.thailam.challenge1.core.utils.toFormattedUsd
import vn.thailam.challenge1.features.list.models.ChequeListItemUiModel

class ChequeListItemUiModelMapper(
    private val statusIconProvider: ChequeListItemStatusIconProvider
) {
    fun mapToUiModel(entities: List<ChequeEntity>): List<ChequeListItemUiModel> {
        return entities.map {
            ChequeListItemUiModel(
                id = it.id.orZero(), // Ensures not null from data layer
                title = it.name.orEmpty(),
                leadingIconUrl = it.iconUrl.orEmpty(),
                displayAmount = it.amount.orEmpty().toFormattedUsd(),
                statusIconRes = statusIconProvider.provide(it.status),
                displayDate = if (it.unixDate.isNullOrZero()) "" else DateFormatUtils.toDate(it.unixDate!!),
                displayStatus = it.status
            )
        }
    }
}
