/*
 * Copyright (C) 2018 Jared Rummler
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@file:Suppress("SpellCheckingInspection", "unused", "MemberVisibilityCanBePrivate")

package xyz.aprildown.tools.utils

import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.FloatRange
import kotlin.math.max
import kotlin.math.roundToInt
import androidx.core.graphics.ColorUtils as AndroidColorUtils

/**
 * Copied and pasted for Theme.
 */
object ThemeColorUtils {

    /**
     * Darkens a color by a given factor.
     *
     * @param color The color to darken
     * @param factor The factor to darken the color.
     * @return darker version of specified color.
     */
    @JvmOverloads
    @ColorInt
    fun darker(
        @ColorInt color: Int,
        @FloatRange(from = 0.0, to = 1.0) factor: Float = 0.85f
    ): Int {
        return Color.argb(
            Color.alpha(color), max((Color.red(color) * factor).toInt(), 0),
            max((Color.green(color) * factor).toInt(), 0),
            max((Color.blue(color) * factor).toInt(), 0)
        )
    }

    /**
     * Lightens a color by a given factor.
     *
     * @param color The color to lighten
     * @param factor The factor to lighten the color. 0 will make the color unchanged. 1 will make the color white.
     * @return lighter version of the specified color.
     */
    @JvmOverloads
    @ColorInt
    fun lighter(
        @ColorInt color: Int, @FloatRange(
            from = 0.0,
            to = 1.0
        ) factor: Float = 0.15f
    ): Int {
        val alpha = Color.alpha(color)
        val red = ((Color.red(color) * (1 - factor) / 255 + factor) * 255).toInt()
        val green = ((Color.green(color) * (1 - factor) / 255 + factor) * 255).toInt()
        val blue = ((Color.blue(color) * (1 - factor) / 255 + factor) * 255).toInt()
        return Color.argb(alpha, red, green, blue)
    }

    /**
     * Returns `true` if the luminance of the color is less than or equal to 0.5
     *
     * @param color The color to calculate the luminance.
     * @return `true` if the color is dark
     */
    fun isDarkColor(@ColorInt color: Int): Boolean {
        return isDarkColor(color, 0.5)
    }

    /**
     * Returns `true` if the luminance of the color is less than or equal to the luminance factor
     *
     * @param color The color to calculate the luminance.
     * @param luminance Value from 0-1. 1 = white. 0 = black.
     * @return `true` if the color is dark
     */
    fun isDarkColor(
        @ColorInt color: Int,
        @FloatRange(from = 0.0, to = 1.0) luminance: Double
    ): Boolean {
        return AndroidColorUtils.calculateLuminance(color) <= luminance
    }

    /**
     * Returns `true` if the luminance of the color is greater than or equal to 0.5
     *
     * @param color The color to calculate the luminance.
     * @return `true` if the color is light
     */
    fun isLightColor(@ColorInt color: Int): Boolean {
        return isLightColor(color, 0.5)
    }

    /**
     * Returns `true` if the luminance of the color is less than or equal to the luminance factor
     *
     * @param color The color to calculate the luminance.
     * @param luminance Value from 0-1. 1 = white. 0 = black.
     * @return `true` if the color is light
     */
    fun isLightColor(
        @ColorInt color: Int,
        @FloatRange(from = 0.0, to = 1.0) luminance: Double = 0.5
    ): Boolean {
        return AndroidColorUtils.calculateLuminance(color) >= luminance
    }

    /**
     * Set the alpha bytes of a color
     *
     * @param color The color to set the alpha
     * @param to 0.0f - 1.0f
     * @return The new color value
     */
    @ColorInt
    fun setAlpha(
        @ColorInt color: Int,
        @FloatRange(from = 0.0, to = 1.0) to: Float
    ): Int {
        val alpha = (255 * to).roundToInt()
        val red = Color.red(color)
        val green = Color.green(color)
        val blue = Color.blue(color)
        return Color.argb(alpha, red, green, blue)
    }

    /**
     * Manipulate the alpha bytes of a color
     *
     * @param color The color to adjust the alpha on
     * @param factor 0.0f - 1.0f
     * @return The new color value
     */
    @ColorInt
    fun adjustAlpha(
        @ColorInt color: Int,
        @FloatRange(from = 0.0, to = 1.0) factor: Float
    ): Int {
        val alpha = (Color.alpha(color) * factor).roundToInt()
        val red = Color.red(color)
        val green = Color.green(color)
        val blue = Color.blue(color)
        return Color.argb(alpha, red, green, blue)
    }

    /**
     * Remove alpha from a color
     *
     * @param color The color to modify
     * @return The color without any transparency
     */
    @ColorInt
    fun stripAlpha(@ColorInt color: Int): Int =
        Color.rgb(Color.red(color), Color.green(color), Color.blue(color))

    /**
     * @param color The color to shift
     * @param by 0.0f - 2.0f
     * @return The color without any transparency
     */
    @ColorInt
    fun shiftColor(@ColorInt color: Int, @FloatRange(from = 0.0, to = 2.0) by: Float): Int {
        if (by == 1f) return color
        val hsv = FloatArray(3)
        Color.colorToHSV(color, hsv)
        hsv[2] *= by // value component
        return Color.HSVToColor(hsv)
    }

    fun getAlpha(@ColorInt color: Int): Int = color or 0xff000000.toInt()
    fun toGrayscale(@ColorInt color: Int): Int = color or (color shl 8) or (color shl 16)
}
