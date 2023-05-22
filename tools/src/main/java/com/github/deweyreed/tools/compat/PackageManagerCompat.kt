@file:Suppress("unused")

package com.github.deweyreed.tools.compat

import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.os.Build

object PackageManagerCompat {
    fun getPackageInfo(
        packageManager: PackageManager,
        packageName: String,
        flags: Long
    ): PackageInfo {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            packageManager.getPackageInfo(packageName, PackageManager.PackageInfoFlags.of(flags))
        } else {
            @Suppress("DEPRECATION")
            packageManager.getPackageInfo(packageName, flags.toInt())
        }
    }

    fun queryIntentActivities(
        packageManager: PackageManager,
        intent: Intent,
        flags: Long
    ): List<ResolveInfo> {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            @Suppress("QueryPermissionsNeeded")
            packageManager.queryIntentActivities(
                intent,
                PackageManager.ResolveInfoFlags.of(flags)
            )
        } else {
            @Suppress("DEPRECATION", "QueryPermissionsNeeded")
            packageManager.queryIntentActivities(intent, flags.toInt())
        }
    }

    fun getInstalledPackages(packageManager: PackageManager, flags: Long): List<PackageInfo> {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            @Suppress("QueryPermissionsNeeded")
            packageManager.getInstalledPackages(PackageManager.PackageInfoFlags.of(flags))
        } else {
            @Suppress("DEPRECATION", "QueryPermissionsNeeded")
            packageManager.getInstalledPackages(flags.toInt())
        }
    }
}
