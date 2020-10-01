package vn.thailam.challenge1.core.widget

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import vn.thailam.challenge1.R

class ChequeSpaceItemDecoration : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)
        val itemCount = parent.adapter?.itemCount ?: 0
        val spacing16 = parent.resources.getDimensionPixelSize(R.dimen.margin_2)
        outRect.top = spacing16 / 2
        outRect.right = spacing16

        // Last item should have max bottom spacing
        outRect.bottom = if (position == itemCount - 1) {
            spacing16
        } else {
            spacing16 / 2
        }

        outRect.left = spacing16
    }
}
