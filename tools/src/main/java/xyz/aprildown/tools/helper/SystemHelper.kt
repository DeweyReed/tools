@file:Suppress("unused")

package xyz.aprildown.tools.helper

import android.app.Activity
import android.app.AppOpsManager
import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Binder
import android.os.Build
import android.provider.Settings
import android.view.View
import android.view.ViewTreeObserver
import android.view.inputmethod.InputMethodManager
import androidx.core.content.getSystemService
import androidx.core.net.ConnectivityManagerCompat

/**
 * Created on 2018/6/19.
 */

fun Context.isNetworkCheap(): Boolean {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return !ConnectivityManagerCompat.isActiveNetworkMetered(cm)
}

fun Context.isNetworkConnected(): Boolean {
    val cm = getSystemService<ConnectivityManager>() ?: return false
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        cm.getNetworkCapabilities(cm.activeNetwork)
            ?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
    } else {
        @Suppress("DEPRECATION")
        cm.activeNetworkInfo?.isConnectedOrConnecting == true
    }
}

fun Context.isPipSupported(): Boolean {
    return isOOrLater() && packageManager.hasSystemFeature(
        PackageManager.FEATURE_PICTURE_IN_PICTURE
    )
}

/**
 * https://developer.squareup.com/blog/showing-the-android-keyboard-reliably/
 */
fun View.focusAndShowKeyboard() {
    /**
     * This is to be called when the window already has focus.
     */
    fun View.showTheKeyboardNow() {
        if (isFocused) {
            post {
                // We still post the call, just in case we are being notified of the windows focus
                // but InputMethodManager didn't get properly setup yet.
                val imm =
                    context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
            }
        }
    }

    requestFocus()
    if (hasWindowFocus()) {
        // No need to wait for the window to get focus.
        showTheKeyboardNow()
    } else {
        // We need to wait until the window gets focus.
        viewTreeObserver.addOnWindowFocusChangeListener(
            object : ViewTreeObserver.OnWindowFocusChangeListener {
                override fun onWindowFocusChanged(hasFocus: Boolean) {
                    // This notification will arrive just before the InputMethodManager gets set up.
                    if (hasFocus) {
                        this@focusAndShowKeyboard.showTheKeyboardNow()
                        // It’s very important to remove this listener once we are done.
                        viewTreeObserver.removeOnWindowFocusChangeListener(this)
                    }
                }
            })
    }
}

fun Activity.hideKeyboard() {
    val view = currentFocus ?: findViewById(android.R.id.content)
    if (view != null) {
        getSystemService<InputMethodManager>()?.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

fun Context.canDrawOverlays(): Boolean = if (isMOrLater()) {
    Settings.canDrawOverlays(this)
} else {
    try {
        val manager = getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
        val dispatchMethod = AppOpsManager::class.java.getMethod(
            "checkOp",
            Int::class.javaPrimitiveType,
            Int::class.javaPrimitiveType,
            String::class.java
        )
        // AppOpsManager.OP_SYSTEM_ALERT_WINDOW = 24
        AppOpsManager.MODE_ALLOWED == dispatchMethod.invoke(
            manager,
            24,
            Binder.getCallingUid(),
            packageName
        ) as Int
    } catch (e: Exception) {
        false
    }
}
