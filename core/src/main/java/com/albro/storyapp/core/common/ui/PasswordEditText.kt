package com.albro.storyapp.core.common.ui

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import com.albro.storyapp.core.R
import com.google.android.material.textfield.TextInputEditText

class PasswordEditText : TextInputEditText {
    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context,
        attrs,
        defStyleAttr) {
        init()
    }

    private fun showErrorMessage() {
        error = context.getString(R.string.invalid_password)
    }

    private fun clearErrorMessage() {
        error = null
    }

    private fun init() {
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit

            override fun onTextChanged(text: CharSequence, p1: Int, p2: Int, p3: Int) {
                if (text.length < 8 && text.isNotEmpty()) showErrorMessage()
                else clearErrorMessage()
            }

            override fun afterTextChanged(p0: Editable?) = Unit
        })
    }
}