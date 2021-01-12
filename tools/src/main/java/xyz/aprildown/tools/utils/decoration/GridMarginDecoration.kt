@file:Suppress("unused")

package xyz.aprildown.tools.utils.decoration

import android.graphics.Rect
import android.view.View
import androidx.annotation.Px
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Requires spanSize == 1.
 */
class GridMarginDecoration(
    @Px private val margin: Int,
    @Px private val horizontalMargin: Int = 0,
    @Px private val verticalMargin: Int = 0,
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val lm = parent.layoutManager as? GridLayoutManager ?: return
        val position = parent.getChildAdapterPosition(view)
        val itemCount = lm.itemCount
        val spanCount = lm.spanCount

        // Handle other items

        fun applyHorizontalMargin() {
            when (position % spanCount) {
                0 -> {
                    if (horizontalMargin != 0) {
                        outRect.left = horizontalMargin
                    }
                    outRect.right = margin / 2
                }
                spanCount - 1 -> {
                    outRect.left = margin / 2
                    if (horizontalMargin != 0) {
                        outRect.right = horizontalMargin
                    }
                }
                else -> {
                    outRect.left = margin / 2
                    outRect.right = margin / 2
                }
            }
        }

        when {
            itemCount == 0 -> return
            itemCount <= spanCount -> {
                applyHorizontalMargin()
                if (verticalMargin != 0) {
                    outRect.top = verticalMargin
                    outRect.bottom = verticalMargin
                }
            }
            itemCount > spanCount -> {
                applyHorizontalMargin()
                when {
                    position < spanCount -> {
                        // The first line
                        if (verticalMargin != 0) {
                            outRect.top = verticalMargin
                        }
                        outRect.bottom = margin / 2
                    }
                    position >= itemCount - spanCount -> {
                        // The last line
                        outRect.top = margin / 2
                        if (verticalMargin != 0) {
                            outRect.bottom = verticalMargin
                        }
                    }
                    else -> {
                        outRect.top = margin / 2
                        outRect.bottom = margin / 2
                    }
                }
            }
        }
    }
}
