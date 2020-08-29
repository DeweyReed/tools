@file:Suppress("unused")

package xyz.aprildown.tools.utils.decoration

import android.graphics.Rect
import android.view.View
import androidx.annotation.Px
import androidx.recyclerview.widget.RecyclerView

class VerticalLinearMarginDecoration(
    @Px private val margin: Int,
    @Px private val edgeMargin: Int = 0,
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val lm = parent.layoutManager ?: return
        val position = parent.getChildAdapterPosition(view)
        val itemCount = lm.itemCount

        when {
            itemCount == 0 -> return
            itemCount == 1 && edgeMargin == 0 -> return
            itemCount == 1 && edgeMargin != 0 -> {
                outRect.top = edgeMargin
                outRect.bottom = edgeMargin
            }
            position == 0 -> {
                if (edgeMargin != 0) {
                    outRect.top = edgeMargin
                }
                outRect.bottom = margin / 2
            }
            position == itemCount - 1 -> {
                if (edgeMargin != 0) {
                    outRect.bottom = edgeMargin
                }
                outRect.top = margin / 2
            }
            else -> {
                outRect.top = margin / 2
                outRect.bottom = margin / 2
            }
        }
    }
}
