@file:Suppress("unused", "ObsoleteSdkInt", "NOTHING_TO_INLINE")

package xyz.aprildown.tools.helper

import android.os.Build

/**
 * Created on 2017/11/3.
 * IDE won't recognize property versions.
 * So I have to use functions.
 */

inline fun isKitKatOrLater(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT

inline fun isLOrLater(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP

inline fun isMOrLater(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

inline fun isNOrLater(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N

inline fun isNMR1OrLater(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1

inline fun isOOrLater(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O

inline fun isOMr1orLater(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1

inline fun isPOrLater(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.P

inline fun isQOrLater(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q

inline fun isROrLater(): Boolean =
    Build.VERSION.SDK_INT >= 30 // TODO: 2020/9/14 : Replace it with R.
