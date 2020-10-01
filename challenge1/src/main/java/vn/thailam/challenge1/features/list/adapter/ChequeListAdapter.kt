package vn.thailam.challenge1.features.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.annotation.AnimRes
import androidx.annotation.AnimatorRes
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_cheque_list.view.*
import vn.thailam.challenge1.R
import vn.thailam.challenge1.features.list.models.ChequeListItemUiModel

class ChequeListAdapter(
    private val clickListener: ClickListener
) : ListAdapter<ChequeListItemUiModel, ChequeListAdapter.ViewHolder>(ChequeListItemDiffUtil()) {

    @AnimRes
    @AnimatorRes
    private var animResId: Int? = R.anim.anim_slide_from_bottom
    private var lastPosition = -1 // helper for making animation for on scroll to item ( trigger once per item per refresh )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cheque_list, parent, false)
        return ViewHolder(clickListener, view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        setItemEnterAnimation(holder.itemView, position)
        holder.bind(item, position)
    }

    fun resetEnterAnimation() {
        lastPosition = -1
    }

    /**
     * set the enter animation for item, only items that are newly added below will have this animation ( through #lastPosition )
     * @param viewToAnimate: The view to be animated
     * @param position: position of item in list
     */
    private fun setItemEnterAnimation(viewToAnimate: View, position: Int) {
        if (position > lastPosition && animResId != null) {
            val animation = AnimationUtils.loadAnimation(viewToAnimate.context, animResId!!)
            viewToAnimate.startAnimation(animation)
            lastPosition = position
        }
    }

    class ViewHolder(
        private val clickListener: ClickListener,
        view: View
    ) : RecyclerView.ViewHolder(view) {

        fun bind(item: ChequeListItemUiModel, position: Int) {

            itemView.clChequeItemContainer.transitionName = item.title

            Glide.with(itemView.context.applicationContext)
                .load(item.leadingIconUrl)
                .circleCrop()
                .into(itemView.ivChequeLeadingIcon)

            itemView.tvChequeTitle.text = item.title
            itemView.tvChequeAmount.text = item.displayAmount
            itemView.tvChequeDate.text = item.displayDate
            itemView.tvChequeStatus.text = item.displayStatus

            item.statusIconRes?.let {
                itemView.ivChequeStatusIcon.setImageDrawable(ResourcesCompat.getDrawable(itemView.resources, it, null))
            }

            itemView.setOnClickListener {
                clickListener.onItemClick(item, itemView.clChequeItemContainer, position)
            }
        }
    }

    interface ClickListener {
        fun onItemClick(item: ChequeListItemUiModel, sharedElementView: View, position: Int)
    }
}
