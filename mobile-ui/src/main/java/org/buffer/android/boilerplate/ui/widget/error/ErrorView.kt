package org.buffer.android.boilerplate.ui.widget.error

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.view_error.view.*
import org.buffer.android.boilerplate.ui.R

/**
 * Widget used to display an empty state to the user
 */
class ErrorView : RelativeLayout {

    var errorListener: ErrorListener? = null

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        LayoutInflater.from(context).inflate(R.layout.view_error, this)
        button_try_again.setOnClickListener { errorListener?.onTryAgainClicked() }
    }

}