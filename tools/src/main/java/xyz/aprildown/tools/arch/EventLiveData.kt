@file:Suppress("unused")

package xyz.aprildown.tools.arch

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

@Deprecated("Use Event directly.")
class EventLiveData<T> : MutableLiveData<EventLiveData.Event<T>>() {

    /**
     * https://medium.com/androiddevelopers/livedata-with-snackbar-navigation-and-other-events-the-singleliveevent-case-ac2622673150#0e87
     * Used as a wrapper for data that is exposed via a LiveData that represents an event.
     */
    open class Event<out Type>(private val content: Type) {

        var hasBeenHandled = false
            private set // Allow external read but not write

        /**
         * Returns the content and prevents its use again.
         */
        fun consumeContent(): Type {
            require(!hasBeenHandled)
            hasBeenHandled = true
            return content
        }

        /**
         * Returns the content, even if it's already been handled.
         */
        fun peekContent(): Type = content
    }

    fun setEvent(value: T) {
        setValue(Event(value))
    }
}

@Deprecated("Use Event directly.", ReplaceWith(""))
fun <T> LifecycleOwner.observeEvent(
    liveData: LiveData<EventLiveData.Event<T>>,
    body: (T) -> Unit
) {
    liveData.removeObservers(this)
    liveData.observe(this, { content ->
        if (content != null && !content.hasBeenHandled) {
            body.invoke(content.consumeContent())
        }
    })
}

@Deprecated("Use Event directly.", ReplaceWith(""))
fun <T> Fragment.observeViewEvent(
    liveData: LiveData<EventLiveData.Event<T>>,
    body: (T) -> Unit
) {
    viewLifecycleOwner.observeEvent(liveData, body)
}
