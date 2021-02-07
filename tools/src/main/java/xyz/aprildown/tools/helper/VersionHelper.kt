@file:Suppress("unused", "ObsoleteSdkInt", "NOTHING_TO_INLINE")

package xyz.aprildown.tools.helper

import android.os.Build

/**
 * Created on 2017/11/3.
 * IDE won't recognize property versions.
 * So I have to use functions.
 */

/**
 * KitKat: 19
 */
inline fun isKitKatOrLater(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT

/**
 * Lollipop: 21
 */
inline fun isLOrLater(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP

/**
 * M: 23
 */
inline fun isMOrLater(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

/**
 * N: 24
 */
inline fun isNOrLater(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N

/**
 * N MR1: 25
 */
inline fun isNMr1OrLater(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1

/**
 * O: 26
 */
inline fun isOOrLater(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O

/**
 * O MR1: 27
 */
inline fun isOMr1OrLater(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1

/**
 * P: 28
 */
inline fun isPOrLater(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.P

/**
 * Q: 29
 */
inline fun isQOrLater(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q

/**
 * R: 30
 */
inline fun isROrLater(): Boolean = Build.VERSION.SDK_INT >= 30
