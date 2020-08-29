package xyz.aprildown.tools.helper

import android.view.View
import androidx.recyclerview.widget.RecyclerView

@Suppress("unused")
@Deprecated(message = "Use your own callbacks")
interface RecyclerViewCallbacks {

    interface AdapterSetUp<T> {
        val items: MutableList<T>
        fun get(pos: Int): T = items[pos]
        fun set(new: List<T>) {
            items.clear()
            items.addAll(new)
        }

        fun remove(pos: Int) {
            items.removeAt(pos)
        }
    }

    interface Callback1 {
        fun onItemCallback(view: View, position: Int)
    }

    interface Callback2 {
        fun onItemCallback(viewHolder: RecyclerView.ViewHolder)
    }

    interface Callback3 {
        fun onItemCallback(viewHolder: RecyclerView.ViewHolder, id: Int)
    }

    interface Callback4<T> {
        fun onItemCallback(view: View, pos: Int, item: T)
    }
}
