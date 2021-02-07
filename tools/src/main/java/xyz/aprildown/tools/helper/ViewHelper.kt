@file:Suppress("NOTHING_TO_INLINE", "unused")

package xyz.aprildown.tools.helper

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.drawable.Animatable
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.TypedValue
import android.view.View
import android.view.ViewPropertyAnimator
import android.view.inputmethod.EditorInfo
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.SeekBar
import android.widget.Spinner
import android.widget.TextView
import android.widget.TimePicker
import androidx.annotation.IdRes
import androidx.core.view.GestureDetectorCompat
import androidx.core.view.postDelayed
import androidx.core.view.postOnAnimationDelayed
import com.google.android.material.tabs.TabLayout
import xyz.aprildown.tools.R

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

infix fun TextView.setTextIfChanged(newText: CharSequence) {
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

fun EditText.setTextAndSelectEnd(text: String) {
    setText(text)
    text.length.let {
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

abstract class AbstractTextWatcher : TextWatcher {
    override fun afterTextChanged(s: Editable?) = Unit
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit
}

// endregion EditText

abstract class AbstractOnTabSelectedListener : TabLayout.OnTabSelectedListener {
    override fun onTabReselected(tab: TabLayout.Tab) = Unit
    override fun onTabUnselected(tab: TabLayout.Tab) = Unit
    override fun onTabSelected(tab: TabLayout.Tab) = Unit
}

fun <T : View> Dialog.requireViewByIdCompat(@IdRes idRes: Int): T =
    findViewById(idRes) ?: throw IllegalStateException()

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

// region TimePicker

@Suppress("DEPRECATION")
fun TimePicker.putHour(hour: Int): Unit = if (isMOrLater()) setHour(hour) else currentHour = hour

@Suppress("DEPRECATION")
fun TimePicker.retrieveHour(): Int = if (isMOrLater()) hour else currentHour

@Suppress("DEPRECATION")
fun TimePicker.putMinute(minute: Int): Unit =
    if (isMOrLater()) setMinute(minute) else currentMinute = minute

@Suppress("DEPRECATION")
fun TimePicker.retrieveMinute(): Int = if (isMOrLater()) minute else currentMinute

// endregion TimePicker


// region Spinner

fun Spinner.setItems(items: List<String>) {
    adapter = ArrayAdapter(
        context,
        R.layout.support_simple_spinner_dropdown_item,
        items
    )
}

fun Spinner.setSelectIndex(index: Int) {
    if (index in 0 until adapter.count) {
        setSelection(index)
    }
}

fun Spinner.setSelectedListener(listener: (spinner: Spinner, position: Int) -> Unit) {
    onItemSelectedListener = object : AbstractOnItemSelectedListener() {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            listener.invoke(this@setSelectedListener, position)
        }
    }
}

abstract class AbstractOnItemSelectedListener : AdapterView.OnItemSelectedListener {
    override fun onNothingSelected(parent: AdapterView<*>?) = Unit
}

// endregion Spinner

abstract class AbstractOnSeekBarChangeListener : SeekBar.OnSeekBarChangeListener {
    override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) = Unit
    override fun onStartTrackingTouch(seekBar: SeekBar) = Unit
    override fun onStopTrackingTouch(seekBar: SeekBar) = Unit
}
