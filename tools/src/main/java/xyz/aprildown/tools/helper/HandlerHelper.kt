@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package xyz.aprildown.tools.helper

import android.os.Handler
import android.os.Looper

object HandlerHelper {
    val handler by lazy { Handler(Looper.getMainLooper()) }

    fun runOnUiThread(runnable: () -> Unit) {
        if (Looper.myLooper() === Looper.getMainLooper()) {
            runnable.invoke()
        } else {
            handler.post(runnable)
        }
    }
}
