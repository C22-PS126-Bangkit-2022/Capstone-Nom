package com.bangkit.capstonenom.customview

import android.content.Context
import android.graphics.Canvas
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Patterns.EMAIL_ADDRESS
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.bangkit.capstonenom.R

class textCustomView : AppCompatEditText {

    var type = ""

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }

    private fun init() {
        background = ContextCompat.getDrawable(context, R.drawable.bg_customview)

        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (type == "email") {
                    if (!EMAIL_ADDRESS.matcher(s).matches()) {
                        error = context.getString(R.string.invalid_email)
                    }
                } else if (type == "password") {
                    if (s.length < 6) {
                        error = context.getString(R.string.password_more_6)
                    }
                } else {
                    if (s.isEmpty()) {
                        error = context.getString(R.string.invalid_name)
                    }
                }
            }


            override fun afterTextChanged(s: Editable?) {

            }
        })
    }


}