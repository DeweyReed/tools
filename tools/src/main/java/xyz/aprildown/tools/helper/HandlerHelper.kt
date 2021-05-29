@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package xyz.aprildown.tools.helper

import android.os.Handler
import android.os.Looper

object HandlerHelper {
    private val handler by lazy { Handler(Looper.getMainLooper()) }

    fun runOnUiThread(runnable: () -> Unit) {
        if (Looper.myLooper() === Looper.getMainLooper()) {
            runnable.invoke()
        } else {
            handler.post(runnable)
        }
    }

    fun post(runnable: Runnable) {
        handler.post(runnable)
    }

    fun postDelayed(delayMillis: Long, runnable: Runnable) {
        handler.postDelayed(runnable, delayMillis)
    }

    fun remove(runnable: Runnable) {
        handler.removeCallbacks(runnable)
    }
}
