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
        val itemCount = lm.itemCount
        if (itemCount <= 1) return

        val spanCount = lm.spanCount
        val position = parent.getChildAdapterPosition(view)

        val horizontalPosition = position % spanCount
        if (horizontalPosition < spanCount - 1) {
            outRect.right = horizontalMargin
        }

        var lines = itemCount / spanCount
        if (horizontalPosition > 0) {
            ++lines
        }

        if (position / spanCount < lines - 1) {
            outRect.bottom = verticalMargin
        }
    }
}
