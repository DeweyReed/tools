@file:Suppress("unused")

package xyz.aprildown.tools.helper

import android.os.Handler
import android.os.Looper

private typealias DeleteAction = () -> Unit

/**
 * Created on 2018/8/2.
 */
class SoftDeleteHelper(private val timeToUndo: Long = DEFAULT_TIME) {

    private val handler = Handler(Looper.getMainLooper())
    private var actions = mutableListOf<DeleteAction>()

    val actionSize: Int
        get() = actions.size

    fun schedule(runnable: DeleteAction) {
        handler.removeCallbacksAndMessages(null)
        actions.add(runnable)
        handler.postDelayed({
            actions.forEach {
                it.invoke()
            }
            actions.clear()
        }, timeToUndo)
    }

    fun undo() {
        handler.removeCallbacksAndMessages(null)
        actions.clear()
    }

    fun execute() {
        handler.removeCallbacksAndMessages(null)
        actions.forEach {
            it.invoke()
        }
        actions.clear()
    }
}

private const val DEFAULT_TIME = 4000L
