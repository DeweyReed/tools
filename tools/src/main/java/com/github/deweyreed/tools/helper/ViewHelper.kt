@file:Suppress("NOTHING_TO_INLINE", "unused")

package com.github.deweyreed.tools.helper

import android.annotation.SuppressLint
import android.graphics.drawable.Animatable
import android.text.InputType
import android.util.TypedValue
import android.view.View
import android.view.ViewPropertyAnimator
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import androidx.core.view.GestureDetectorCompat
import androidx.core.view.postDelayed
import androidx.core.view.postOnAnimationDelayed
import com.github.deweyreed.tools.R
import com.google.android.material.tabs.TabLayout

/**
 * Created on 2017/11/5.
 */

inline fun View.onLongClick(crossinline body: (View) -> Unit) {
    setOnLongClickListener {
        body.invoke(it)
        true
    }
}

inline fun View.show() {
    visibility = View.VISIBLE
}

inline fun View.hide() {
    visibility = View.INVISIBLE
}

inline fun View.gone() {
    visibility = View.GONE
}

inline fun View.setScale(value: Float) {
    scaleX = value
    scaleY = value
}

fun View.setSelectableItemBackground() {
    val outValue = TypedValue()
    context.theme.resolveAttribute(R.attr.selectableItemBackground, outValue, true)
    setBackgroundResource(outValue.resourceId)
}

fun View.triggerRipple(duration: Long = 100) {
    postOnAnimationDelayed(duration) {
        isPressed = true
        postDelayed(duration) {
            isPressed = false
        }
    }
}

inline fun ViewPropertyAnimator.scale(value: Float): ViewPropertyAnimator =
    scaleX(value).scaleY(value)

fun ImageView.startDrawableAnimation() {
    (drawable as? Animatable)?.start()
}

fun ImageView.stopDrawableAnimation() {
    (drawable as? Animatable)?.run { if (isRunning) stop() }
}

@SuppressLint("ClickableViewAccessibility")
fun GestureDetectorCompat.attachToView(view: View) {
    view.setOnTouchListener { _, event ->
        onTouchEvent(event)
        true
    }
}

//
// TextView
//

infix fun TextView.setTextIfChanged(newText: CharSequence?) {
    if (text != newText) text = newText
}

// region EditText

/**
 * @param targetActionId [EditorInfo.IME_ACTION_DONE], etc.
 */
fun EditText.onImeActionClick(targetActionId: Int, onClick: () -> Unit) {
    setOnEditorActionListener { _, actionId, _ ->
        if (actionId == targetActionId) {
            onClick.invoke()
            true
        } else {
            false
        }
    }
}

fun EditText.setTextAndSelectEnd(text: CharSequence?) {
    setText(text)
    text?.length?.let {
        if (it > 0) {
            post {
                try {
                    setSelection(it)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}

/**
 * Ensure <code>android:inputType="text|textMultiLine"</code> in the XML.
 */
fun EditText.showActionAndMultiLine(toImeOptions: Int) {
    // https://stackoverflow.com/a/41022589/5507158
    imeOptions = toImeOptions
    setRawInputType(InputType.TYPE_CLASS_TEXT)
}

// endregion EditText

abstract class AbstractOnTabSelectedListener : TabLayout.OnTabSelectedListener {
    override fun onTabReselected(tab: TabLayout.Tab) = Unit
    override fun onTabUnselected(tab: TabLayout.Tab) = Unit
    override fun onTabSelected(tab: TabLayout.Tab) = Unit
}

// region Scroll

fun ScrollView.scrollToBottom() {
    post {
        val lastChild = getChildAt(childCount - 1)
        val bottom = lastChild.bottom + paddingBottom
        val delta = bottom - (scrollY + height)
        smoothScrollBy(0, delta)
    }
}

// endregion Scroll
