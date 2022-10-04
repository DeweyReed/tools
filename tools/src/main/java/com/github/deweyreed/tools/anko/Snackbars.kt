@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.github.deweyreed.tools.anko

import android.view.View
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar

// region Short Snackbar

inline fun View.snackbar(@StringRes message: Int) = Snackbar
    .make(this, message, Snackbar.LENGTH_SHORT)
    .apply { show() }

inline fun View.snackbar(message: CharSequence) = Snackbar
    .make(this, message, Snackbar.LENGTH_SHORT)
    .apply { show() }

inline fun View.snackbar(
    message: Int,
    @StringRes actionText: Int,
    noinline action: (View) -> Unit
) = Snackbar
    .make(this, message, Snackbar.LENGTH_SHORT)
    .setAction(actionText, action)
    .apply { show() }

inline fun View.snackbar(
    message: CharSequence,
    actionText: CharSequence,
    noinline action: (View) -> Unit
) = Snackbar
    .make(this, message, Snackbar.LENGTH_SHORT)
    .setAction(actionText, action)
    .apply { show() }

// endregion

// region Long Snackbar

inline fun View.longSnackbar(@StringRes message: Int) = Snackbar
    .make(this, message, Snackbar.LENGTH_LONG)
    .apply { show() }

inline fun View.longSnackbar(message: CharSequence) = Snackbar
    .make(this, message, Snackbar.LENGTH_LONG)
    .apply { show() }

inline fun View.longSnackbar(
    @StringRes message: Int,
    @StringRes actionText: Int,
    noinline action: (View) -> Unit
) = Snackbar
    .make(this, message, Snackbar.LENGTH_LONG)
    .setAction(actionText, action)
    .apply { show() }

inline fun View.longSnackbar(
    message: CharSequence,
    actionText: CharSequence,
    noinline action: (View) -> Unit
) = Snackbar
    .make(this, message, Snackbar.LENGTH_LONG)
    .setAction(actionText, action)
    .apply { show() }

// endregion

// region Indefinite Snackbar

inline fun View.indefiniteSnackbar(@StringRes message: Int) = Snackbar
    .make(this, message, Snackbar.LENGTH_INDEFINITE)
    .apply { show() }

inline fun View.indefiniteSnackbar(message: CharSequence) = Snackbar
    .make(this, message, Snackbar.LENGTH_INDEFINITE)
    .apply { show() }

inline fun View.indefiniteSnackbar(
    @StringRes message: Int,
    @StringRes actionText: Int,
    noinline action: (View) -> Unit
) = Snackbar
    .make(this, message, Snackbar.LENGTH_INDEFINITE)
    .setAction(actionText, action)
    .apply { show() }

inline fun View.indefiniteSnackbar(
    message: CharSequence,
    actionText: CharSequence,
    noinline action: (View) -> Unit
) = Snackbar
    .make(this, message, Snackbar.LENGTH_INDEFINITE)
    .setAction(actionText, action)
    .apply { show() }

// endregion
