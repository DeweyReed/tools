package xyz.aprildown.tools.helper

import android.content.Context
import androidx.core.content.ContextCompat

/**
 * Created on 2017/11/16.
 */

val Context.safeContext: Context
    get() = takeIf { isNOrLater() && !isDeviceProtectedStorage }?.let {
        ContextCompat.createDeviceProtectedStorageContext(it) ?: it
    } ?: this
