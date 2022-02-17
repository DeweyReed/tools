@file:Suppress("unused")

package xyz.aprildown.tools.anko

import android.content.Context
import android.os.Build
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import me.drakeet.support.toast.ToastCompat

private fun Context.createToast(
    @StringRes messageRes: Int = 0,
    message: CharSequence? = null,
    duration: Int = Toast.LENGTH_SHORT,
): Toast {
    return if (Build.VERSION.SDK_INT == Build.VERSION_CODES.N_MR1) {
        if (messageRes != 0) {
            ToastCompat.makeText(this, messageRes, duration)
        } else {
            ToastCompat.makeText(this, message, duration)
        }
    } else {
        if (messageRes != 0) {
            Toast.makeText(this, messageRes, duration)
        } else {
            Toast.makeText(this, message, duration)
        }
    }
}

// region Short Toast

fun Context.toast(@StringRes message: Int): Toast {
    return createToast(messageRes = message, duration = Toast.LENGTH_SHORT).apply { show() }
}

fun Context.toast(message: CharSequence): Toast {
    return createToast(message = message, duration = Toast.LENGTH_SHORT).apply { show() }
}

// endregion

// region Long Toast

fun Context.longToast(@StringRes message: Int): Toast {
    return createToast(messageRes = message, duration = Toast.LENGTH_LONG).apply { show() }
}

fun Context.longToast(message: CharSequence): Toast {
    return createToast(message = message, duration = Toast.LENGTH_LONG).apply { show() }
}

// endregion

// region View

fun View.toast(@StringRes message: Int): Toast = context.toast(message)
fun View.toast(message: CharSequence): Toast = context.toast(message)

fun View.longToast(@StringRes message: Int): Toast = context.longToast(message)
fun View.longToast(message: CharSequence): Toast = context.longToast(message)

// endregion
