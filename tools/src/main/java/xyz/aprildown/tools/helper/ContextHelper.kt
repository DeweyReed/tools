package xyz.aprildown.tools.helper

import android.content.Context
import android.os.Build
import androidx.core.content.ContextCompat

/**
 * Created on 2017/11/16.
 */

val Context.safeContext: Context
    get() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) return this
        if (!isDeviceProtectedStorage) return this
        return ContextCompat.createDeviceProtectedStorageContext(this) ?: this
    }
