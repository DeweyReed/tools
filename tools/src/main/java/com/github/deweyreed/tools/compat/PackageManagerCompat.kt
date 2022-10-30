@file:Suppress("unused")

package com.github.deweyreed.tools.compat

import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.os.Build

fun PackageManager.getPackageInfoCompat(packageName: String, flags: Long): PackageInfo {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getPackageInfo(packageName, PackageManager.PackageInfoFlags.of(flags))
    } else {
        @Suppress("DEPRECATION")
        getPackageInfo(packageName, flags.toInt())
    }
}

fun PackageManager.queryIntentActivitiesCompat(intent: Intent, flags: Long): List<ResolveInfo> {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        @Suppress("QueryPermissionsNeeded")
        queryIntentActivities(intent, PackageManager.ResolveInfoFlags.of(flags))
    } else {
        @Suppress("DEPRECATION", "QueryPermissionsNeeded")
        queryIntentActivities(intent, flags.toInt())
    }
}
