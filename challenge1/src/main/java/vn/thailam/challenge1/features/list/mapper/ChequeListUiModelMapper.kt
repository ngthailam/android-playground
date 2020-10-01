package vn.thailam.challenge1.features.list.mapper

import vn.thailam.challenge1.core.data.models.ChequeEntity
import vn.thailam.challenge1.features.list.models.ChequeListUiModel

class ChequeListUiModelMapper(
    private val ChequeItemMapper: ChequeListItemUiModelMapper
) {
    fun mapToUiModel(entities: List<ChequeEntity>): ChequeListUiModel {
        return ChequeListUiModel(
            // TODO: use this field
            toolbarTitle = "Cheques",
            items = ChequeItemMapper.mapToUiModel(entities)
        )
    }
}
