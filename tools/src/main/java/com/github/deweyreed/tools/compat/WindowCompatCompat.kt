@file:Suppress("unused")

package com.github.deweyreed.tools.compat

import android.view.View
import android.view.Window
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat

fun Window.getInsetsControllerCompat(view: View): WindowInsetsControllerCompat {
    return WindowCompat.getInsetsController(this, view)
}
