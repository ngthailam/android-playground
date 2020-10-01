package vn.thailam.challenge1.features.detail.mapper

import vn.thailam.challenge1.core.data.models.ChequeItemEntity
import vn.thailam.challenge1.core.utils.orZero
import vn.thailam.challenge1.core.utils.toFormattedUsd
import vn.thailam.challenge1.features.detail.models.ChequeItemUiModel

class ChequeItemUiModelMapper {
    fun mapToUiModel(entity: ChequeItemEntity): ChequeItemUiModel {
        return ChequeItemUiModel(
            id = entity.id.orZero(),
            title = entity.name.orEmpty(),
            // TODO: support string resource
            subtitle = "${entity.amountPerItem.orEmpty().toFormattedUsd()} x ${entity.quantity} ( including 10% VAT)",
            displayedTotalAmount = entity.totalAmount.orEmpty().toFormattedUsd()
        )
    }
}
