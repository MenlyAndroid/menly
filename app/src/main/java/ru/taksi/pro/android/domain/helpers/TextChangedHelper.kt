package ru.taksi.pro.android.domain.helpers

import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.textfield.TextInputEditText
import ru.taksi.pro.android.mvvm.data.UserProperties

object TextChangedHelper {

    fun getDateTextWatcher(textInput: TextInputEditText, field: String): TextWatcher =
        object : TextWatcher {
            var current = ""
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                var text = textInput.text
                text?.let {
                    if (it.toString() != current) {
                        var clean = it.replace("[^\\d.]|\\.".toRegex(), "")
                        if (clean.length in 3..4) {
                            current = String.format(
                                "%s/%s", clean.substring(0, 2),
                                clean.substring(2, clean.length)
                            )
                            textInput.setText(current)
                            textInput.setSelection(textInput.text.toString().length)
                        } else if (clean.length > 4) {
                            current = String.format(
                                "%s/%s/%s", clean.substring(0, 2),
                                clean.substring(2, 4),
                                clean.substring(4, clean.length)
                            )
                            textInput.setText(current)
                            textInput.setSelection(textInput.text.toString().length)
                        } else {
                            current = it.toString()
                        }
                        UserProperties.instance.setData(current, field)
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        }

    fun getSeriaEndNumberTextWatcher(
        textInput: TextInputEditText,
        field: String
    ): TextWatcher = object : TextWatcher {
        var current = ""
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            var text = textInput.text
            text?.let {
                if (it.toString() != current) {
                    var clean = it.toString().replace(" ", "")
                    if (clean.length > 4) {
                        current = String.format(
                            "%s %s", clean.substring(0, 4),
                            clean.substring(4, clean.length)
                        )
                        textInput.setText(current)
                        textInput.setSelection(textInput.text.toString().length)
                    }
                    current = it.toString()
                    UserProperties.instance.setData(current, field)
                }
            }
        }

        override fun afterTextChanged(p0: Editable?) {
        }

    }
}