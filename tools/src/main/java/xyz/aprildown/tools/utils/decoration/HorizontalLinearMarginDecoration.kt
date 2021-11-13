@file:Suppress("unused")

package xyz.aprildown.tools.utils.decoration

import android.graphics.Rect
import android.view.View
import androidx.annotation.Px
import androidx.recyclerview.widget.RecyclerView

class HorizontalLinearMarginDecoration(
    @Px private val margin: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val itemCount = parent.adapter?.itemCount ?: 0
        if (itemCount <= 1) return

        if (parent.getChildAdapterPosition(view) < itemCount - 1) {
            outRect.right = margin
        }
    }
}
