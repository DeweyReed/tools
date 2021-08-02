@file:Suppress("unused")

package xyz.aprildown.tools.utils

import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.Adapter<*>.withEmptyView(emptyView: View) {

    fun toggle() {
        emptyView.isVisible = itemCount == 0
    }

    registerAdapterDataObserver(
        object : RecyclerView.AdapterDataObserver() {
            override fun onChanged() {
                toggle()
            }

            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                toggle()
            }

            override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
                toggle()
            }
        }
    )
}
