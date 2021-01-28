@file:Suppress("unused")

package xyz.aprildown.tools.helper

import android.app.Activity
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import xyz.aprildown.tools.R

/**
 * Created on 2018/3/12.
 */

//
// PendingIntent
//

fun Context.pendingServiceIntent(intent: Intent, requestCode: Int = 0): PendingIntent {
    return PendingIntent.getService(this, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT)
}

fun Context.pendingActivityIntent(intent: Intent, requestCode: Int = 0): PendingIntent {
    return PendingIntent.getActivity(this, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT)
}

//
// Activity
//

fun Context.startActivitySafely(intent: Intent, @StringRes wrongMessageRes: Int = 0) {
    try {
        startActivity(intent)
    } catch (e: Exception) {
        if (wrongMessageRes != 0) {
            Toast.makeText(this, wrongMessageRes, Toast.LENGTH_LONG).show()
        }
    }
}

fun Activity.restartWithFading(intent: Intent) {
    startActivity(
        intent/*, ActivityOptions.makeCustomAnimation(this,
                R.anim.fade_in, R.anim.fade_out).toBundle()*/
    )
    finish()
    overridePendingTransition(R.anim.fade_in_short, R.anim.fade_out_short)
}

//
// Fragment
//

fun Fragment.startActivitySafely(intent: Intent, @StringRes wrongMessageRes: Int = 0) {
    try {
        startActivity(intent)
    } catch (e: Exception) {
        if (wrongMessageRes != 0) {
            Toast.makeText(requireContext(), wrongMessageRes, Toast.LENGTH_LONG).show()
        }
    }
}

@Deprecated("Check Fragment.view directly", ReplaceWith(""))
val Fragment.isViewAlive: Boolean
    get() = view != null

inline fun <reified T> Fragment.findCallback(): T? {
    return (parentFragment as? T) ?: (context as? T ?: (activity as? T))
}

inline fun <reified T> Fragment.requireCallback(): T {
    return findCallback() ?: throw IllegalStateException("Cannot find callback ${T::class}")
}
