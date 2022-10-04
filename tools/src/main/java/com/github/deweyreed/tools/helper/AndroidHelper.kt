@file:Suppress("unused")

package com.github.deweyreed.tools.helper

import android.app.Activity
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.Size
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.github.deweyreed.tools.R
import com.github.deweyreed.tools.anko.longToast

/**
 * Created on 2018/3/12.
 */

//
// PendingIntent
//

fun Context.pendingServiceIntent(intent: Intent, requestCode: Int = 0): PendingIntent {
    return PendingIntent.getService(
        this,
        requestCode,
        intent,
        PendingIntent.FLAG_UPDATE_CURRENT or
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                PendingIntent.FLAG_IMMUTABLE
            } else {
                0
            }
    )
}

fun Context.pendingActivityIntent(intent: Intent, requestCode: Int = 0): PendingIntent {
    return PendingIntent.getActivity(
        this,
        requestCode,
        intent,
        PendingIntent.FLAG_UPDATE_CURRENT or
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                PendingIntent.FLAG_IMMUTABLE
            } else {
                0
            }
    )
}

//
// Activity
//

fun Context.startActivityOrNothing(intent: Intent, @StringRes wrongMessageRes: Int = 0) {
    try {
        startActivity(intent)
    } catch (e: Exception) {
        if (wrongMessageRes != 0) {
            longToast(wrongMessageRes)
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

fun Context.hasPermissions(
    @Size(min = 1) vararg permissions: String
): Boolean {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return true

    return permissions.all {
        ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED
    }
}

//
// Fragment
//

fun Fragment.startActivityOrNothing(intent: Intent, @StringRes wrongMessageRes: Int = 0) {
    try {
        startActivity(intent)
    } catch (e: Exception) {
        if (wrongMessageRes != 0) {
            context?.longToast(wrongMessageRes)
        }
    }
}

inline fun <reified T> Fragment.findCallback(): T? {
    return (parentFragment as? T) ?: (context as? T ?: (activity as? T))
}

inline fun <reified T> Fragment.requireCallback(): T {
    return findCallback() ?: throw IllegalStateException("Cannot find callback ${T::class}")
}
