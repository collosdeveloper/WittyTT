package com.task.ui.base.custom

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.TranslateAnimation
import android.widget.LinearLayout

@SuppressLint("CustomViewStyleable")
class MoveUpLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyle, defStyleRes) {

    init {
        visibility = View.INVISIBLE
        slideUp()
    }

    private fun slideUp() {
        postDelayed({
            visibility = VISIBLE
            val animate = TranslateAnimation(
                0F,
                0F,
                height.toFloat(),
                0F
            )
            animate.duration = 500
            animate.fillAfter = true
            startAnimation(animate)
        }, 1500)
    }
}