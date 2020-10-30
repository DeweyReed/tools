@file:Suppress("unused")

package xyz.aprildown.tools.helper

import androidx.annotation.FloatRange
import com.google.android.material.math.MathUtils

/**
 * Returns the linear interpolation of [amount] between [start] and [stop].
 * [MathUtils.lerp]
 */
fun lerp(start: Float, stop: Float, @FloatRange(from = 0.0, to = 1.0) amount: Float): Float {
    val clampedAmount = amount.coerceIn(0f, 1f)
    return (1 - clampedAmount) * start + clampedAmount * stop
}

/**
 * Returns the percentage of [value] between [start] and [stop].
 * https://github.com/Unity-Technologies/UnityCsReference/blob/master/Runtime/Export/Math/Mathf.cs#L369
 */
@FloatRange(from = 0.0, to = 1.0)
fun lerpInverse(start: Float, stop: Float, value: Float): Float {
    if (start == stop) return 0f
    return ((value - start) / (stop - start)).coerceIn(0f, 1f)
}

/**
 * Returns the linear interpolation of [amount] between [start] and [stop].
 * [MathUtils.lerp]
 */
fun lerp(start: Int, stop: Int, @FloatRange(from = 0.0, to = 1.0) amount: Float): Int {
    val clampedAmount = amount.coerceIn(0f, 1f)
    return ((1 - clampedAmount) * start + clampedAmount * stop).toInt()
}

/**
 * Returns the percentage of [value] between [start] and [stop].
 * https://github.com/Unity-Technologies/UnityCsReference/blob/master/Runtime/Export/Math/Mathf.cs#L369
 */
@FloatRange(from = 0.0, to = 1.0)
fun lerpInverse(start: Int, stop: Int, value: Int): Float {
    if (start == stop) return 0f
    return ((value - start).toFloat() / (stop - start).toFloat()).coerceIn(0f, 1f)
}
