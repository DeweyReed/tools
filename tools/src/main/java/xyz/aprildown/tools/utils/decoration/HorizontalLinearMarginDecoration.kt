@file:Suppress("unused")

package xyz.aprildown.tools.utils.decoration

import android.graphics.Rect
import android.view.View
import androidx.annotation.Px
import androidx.recyclerview.widget.RecyclerView

class HorizontalLinearMarginDecoration(
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
                outRect.left = edgeMargin
                outRect.right = edgeMargin
            }
            position == 0 -> {
                if (edgeMargin != 0) {
                    outRect.left = edgeMargin
                }
                outRect.right = margin / 2
            }
            position == itemCount - 1 -> {
                if (edgeMargin != 0) {
                    outRect.right = edgeMargin
                }
                outRect.left = margin / 2
            }
            else -> {
                outRect.left = margin / 2
                outRect.right = margin / 2
            }
        }
    }
}
