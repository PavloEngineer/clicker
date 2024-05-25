package com.game.clicker.presentation.util

import android.widget.EditText
import androidx.core.widget.doOnTextChanged

fun EditText.addTextListener(validationFunction: (String) -> Boolean, errorMessage: String) {
    with(this) {
        doOnTextChanged { text, _, _, _ ->
            error = if (!validationFunction(text.toString())) {
                errorMessage
            } else {
                null
            }
        }
    }
}