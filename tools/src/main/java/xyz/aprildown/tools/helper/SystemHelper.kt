@file:Suppress("unused")

package xyz.aprildown.tools.helper

import android.app.Activity
import android.app.AppOpsManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Binder
import android.os.Build
import android.provider.Settings
import android.view.View
import android.view.ViewTreeObserver
import android.view.inputmethod.InputMethodManager
import androidx.annotation.RequiresApi
import androidx.core.content.getSystemService
import androidx.core.net.ConnectivityManagerCompat

/**
 * Created on 2018/6/19.
 */

fun Context.isNetworkCheap(): Boolean {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return !ConnectivityManagerCompat.isActiveNetworkMetered(cm)
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
    val view = findViewById<View>(android.R.id.content)
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

@RequiresApi(Build.VERSION_CODES.M)
fun Context.overlayPermissionIntent(): Intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION)
    .setData(Uri.parse("package:$packageName"))
