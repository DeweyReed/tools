@file:Suppress("unused")

package xyz.aprildown.tools.helper

import android.os.Handler
import android.os.Looper

/**
 * Created on 2018/8/2.
 */
class SoftDeleteHelper(private val timeToUndo: Long = DEFAULT_TIME) {

    private val handler = Handler(Looper.getMainLooper())
    private var actions = mutableListOf<Runnable>()

    val actionSize: Int
        get() = actions.size

    fun schedule(runnable: Runnable) {
        handler.removeCallbacksAndMessages(null)
        actions.add(runnable)
        handler.postDelayed({
            actions.forEach {
                it.run()
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
            it.run()
        }
        actions.clear()
    }
}

private const val DEFAULT_TIME = 4000L
