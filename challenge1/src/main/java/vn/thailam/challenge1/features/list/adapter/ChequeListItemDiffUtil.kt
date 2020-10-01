package vn.thailam.challenge1.features.list.adapter

import androidx.recyclerview.widget.DiffUtil
import vn.thailam.challenge1.features.list.models.ChequeListItemUiModel

class ChequeListItemDiffUtil : DiffUtil.ItemCallback<ChequeListItemUiModel>() {
    override fun areItemsTheSame(oldItem: ChequeListItemUiModel, newItem: ChequeListItemUiModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ChequeListItemUiModel, newItem: ChequeListItemUiModel): Boolean {
        return oldItem.title == newItem.title &&
                oldItem.leadingIconUrl == newItem.leadingIconUrl &&
                oldItem.displayAmount == newItem.displayAmount &&
                oldItem.statusIconRes == newItem.statusIconRes &&
                oldItem.displayDate == newItem.displayDate &&
                oldItem.displayStatus == newItem.displayStatus
    }
}
