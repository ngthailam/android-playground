package vn.thailam.challenge1.features.detail.adapter

import androidx.recyclerview.widget.DiffUtil
import vn.thailam.challenge1.features.detail.models.ChequeItemUiModel

class ChequeItemDiffUtil : DiffUtil.ItemCallback<ChequeItemUiModel>() {
    override fun areItemsTheSame(oldItem: ChequeItemUiModel, newItem: ChequeItemUiModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ChequeItemUiModel, newItem: ChequeItemUiModel): Boolean {
        return oldItem == newItem
    }

}