package vn.thailam.challenge1.features.detail.mapper

import vn.thailam.challenge1.core.data.models.ChequeEntity
import vn.thailam.challenge1.core.utils.DateFormatUtils
import vn.thailam.challenge1.core.utils.isNullOrZero
import vn.thailam.challenge1.core.utils.orZero
import vn.thailam.challenge1.core.utils.toFormattedUsd
import vn.thailam.challenge1.features.detail.models.ChequeDetailUiModel
import vn.thailam.challenge1.features.list.mapper.ChequeListItemStatusIconProvider

class ChequeDetailUiModelMapper(
    private val itemUiModelMapper: ChequeItemUiModelMapper,
    private val statusIconProvider: ChequeListItemStatusIconProvider
) {
    fun mapToUiModel(entity: ChequeEntity): ChequeDetailUiModel {
        return ChequeDetailUiModel(
            id = entity.id.orZero(),
            title = entity.name.orEmpty(),
            displayAmount = entity.amount.orEmpty().toFormattedUsd(),
            displayDate = if (entity.unixDate.isNullOrZero()) "" else DateFormatUtils.toDate(entity.unixDate!!),
            displayStatus = entity.status,
            leadingIconUrl = entity.iconUrl.orEmpty(),
            statusIconRes = statusIconProvider.provide(entity.status),
            items = entity.items?.map {
                itemUiModelMapper.mapToUiModel(it)
            }.orEmpty()
        )
    }
}
