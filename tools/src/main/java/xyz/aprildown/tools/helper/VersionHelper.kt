@file:Suppress("unused", "ObsoleteSdkInt")

package xyz.aprildown.tools.helper

import android.os.Build

/**
 * Created on 2017/11/3.
 * IDE won't recognize property versions.
 * So I have to use functions.
 */

fun isKitKatOrLater(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT

fun isLOrLater(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP

fun isMOrLater(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

fun isNOrLater(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N

fun isNMR1OrLater(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1

fun isOOrLater(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O

fun isOMr1orLater(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1

fun isPOrLater(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.P

fun isQOrLater(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q
