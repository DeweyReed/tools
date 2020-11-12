@file:Suppress("unused")

package xyz.aprildown.tools.view

import android.content.Context
import android.text.InputType
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import xyz.aprildown.tools.R
import xyz.aprildown.tools.helper.gone
import xyz.aprildown.tools.helper.onDoneClick
import xyz.aprildown.tools.helper.setTextAndSelectEnd

class MaterialSimpleInputDialog(
    private val context: Context
) {
    fun show(
        title: String? = null,
        @StringRes titleRes: Int = 0,

        preFill: String? = null,
        inputType: Int = InputType.TYPE_CLASS_TEXT,

        message: String? = null,
        @StringRes messageRes: Int = 0,

        onInput: (String) -> Unit
    ) {
        val builder = MaterialAlertDialogBuilder(context)
            .setPositiveButton(android.R.string.ok, null)
            .setNegativeButton(android.R.string.cancel, null)

        val view = View.inflate(context, R.layout.dialog_material_simple_input, null) as ViewGroup
        val textInputLayout = view.getChildAt(0) as TextInputLayout
        val textInputEditText = textInputLayout.editText as TextInputEditText
        val textView = view.getChildAt(1) as TextView
        textInputEditText.requestFocus()

        builder.setView(view)

        val dialog = builder.create()
        dialog.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE)
        dialog.show()
        dialog.setOnDismissListener {
            dialog.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        }

        when {
            title != null -> {
                textInputLayout.hint = title
            }
            titleRes != 0 -> {
                textInputLayout.hint = context.getText(titleRes)
            }
        }

        val positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
        textInputEditText.onDoneClick {
            positiveButton.performClick()
        }

        if (preFill != null && preFill.isNotBlank()) {
            textInputEditText.setTextAndSelectEnd(preFill)
        }
        textInputEditText.inputType = textInputEditText.inputType or inputType

        if (message == null && messageRes == 0) {
            textView.gone()
        } else {
            textView.text = message ?: context.getString(messageRes)
        }

        positiveButton.setOnClickListener {
            onInput.invoke(textInputEditText.text.toString())
            dialog.dismiss()
        }
    }
}
