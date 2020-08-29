@file:Suppress("unused")

package xyz.aprildown.tools.utils.decoration

import android.content.Context
import android.graphics.drawable.InsetDrawable
import androidx.recyclerview.widget.DividerItemDecoration
import xyz.aprildown.tools.R

class DividerItemDecorationWithMargin(
    context: Context,
    orientation: Int = VERTICAL
) : DividerItemDecoration(context, orientation) {

    init {
        // The divider resource is from DividerItemDecoration
        val a = context.obtainStyledAttributes(intArrayOf(android.R.attr.listDivider))
        val divider = a.getDrawable(0)
        a.recycle()

        val inset = context.resources.getDimensionPixelSize(R.dimen.keyline_icon)
        val insetDivider = InsetDrawable(divider, inset, 0, inset, 0)

        setDrawable(insetDivider)
    }
}
