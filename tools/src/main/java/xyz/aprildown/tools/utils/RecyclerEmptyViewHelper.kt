@file:Suppress("unused")

package xyz.aprildown.tools.utils

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import xyz.aprildown.tools.helper.gone
import xyz.aprildown.tools.helper.show

fun RecyclerView.withEmptyView(emptyView: View) {
    val adapter = adapter ?: throw IllegalStateException("Set adapter before using empty view")

    fun toggle() {
        if (adapter.itemCount == 0) {
            emptyView.show()
            this.gone()
        } else {
            this.show()
            emptyView.gone()
        }
    }

    adapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
        override fun onChanged() {
            toggle()
        }

        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            toggle()
        }

        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            toggle()
        }
    })
}
