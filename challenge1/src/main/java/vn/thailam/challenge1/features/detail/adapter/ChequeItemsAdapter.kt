package vn.thailam.challenge1.features.detail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_cheque_detail_item.view.*
import vn.thailam.challenge1.R
import vn.thailam.challenge1.features.detail.models.ChequeItemUiModel

class ChequeItemsAdapter(
    private val clickListener: ItemClickListener
) : ListAdapter<ChequeItemUiModel, ChequeItemsAdapter.ViewHolder>(ChequeItemDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cheque_detail_item, parent, false)
        return ViewHolder(clickListener, view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder(private val clickListener: ItemClickListener, view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: ChequeItemUiModel) {
            itemView.tvChequeItemTitle.text = item.title
            itemView.tvChequeItemSubtitle.text = item.subtitle
            itemView.tvChequeItemTotalAmount.text = item.displayedTotalAmount
            itemView.setOnClickListener {
                clickListener.onClick(item)
            }
        }
    }

    interface ItemClickListener {
        fun onClick(item: ChequeItemUiModel)
    }
}