@file:Suppress("NOTHING_TO_INLINE", "unused")

package xyz.aprildown.tools.anko

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

// region Short Toast

inline fun Context.toast(@StringRes message: Int): Toast = Toast
    .makeText(this, message, Toast.LENGTH_SHORT)
    .apply {
        show()
    }

inline fun Context.toast(message: CharSequence): Toast = Toast
    .makeText(this, message, Toast.LENGTH_SHORT)
    .apply {
        show()
    }

// endregion

// region Long Toast

inline fun Context.longToast(@StringRes message: Int): Toast = Toast
    .makeText(this, message, Toast.LENGTH_LONG)
    .apply {
        show()
    }

inline fun Context.longToast(message: CharSequence): Toast = Toast
    .makeText(this, message, Toast.LENGTH_LONG)
    .apply {
        show()
    }

// endregion

// region Fragment

inline fun Fragment.toast(@StringRes message: Int): Toast = requireContext().toast(message)
inline fun Fragment.toast(message: CharSequence): Toast = requireContext().toast(message)

inline fun Fragment.longToast(@StringRes message: Int): Toast = requireContext().longToast(message)
inline fun Fragment.longToast(message: CharSequence): Toast = requireContext().longToast(message)

// endregion
