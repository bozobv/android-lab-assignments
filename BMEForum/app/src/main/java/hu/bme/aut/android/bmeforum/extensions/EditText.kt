package hu.bme.aut.android.bmeforum.extensions

import android.widget.EditText


fun EditText.validateNonEmpty(): Boolean {
        if (text.isEmpty()) {
            error = "Required"
            return false
        }
        return true
    }



