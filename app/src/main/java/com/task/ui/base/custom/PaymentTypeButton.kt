package com.task.ui.base.custom

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.task.R
import kotlinx.android.synthetic.main.paymenttype_button_layout.view.*

@SuppressLint("CustomViewStyleable")
class PaymentTypeButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyle, defStyleRes) {

    init {
        LayoutInflater.from(context).inflate(R.layout.paymenttype_button_layout, this, true)
        orientation = VERTICAL

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(
                it,
                R.styleable.paymenttype_button_attrs,
                0,
                0
            )
            val title = resources.getText(
                typedArray
                    .getResourceId(
                        R.styleable.paymenttype_button_attrs_paymenttype_button_title,
                        R.string.title
                    )
            )
            val img = ContextCompat.getDrawable(context, typedArray
                    .getResourceId(R.styleable.paymenttype_button_attrs_paymenttype_button_img, -1))
            val topSeparatorVisibility = typedArray.getBoolean(
                R.styleable.paymenttype_button_attrs_paymenttype_button_topseparator_enable,
                false)

            top_separator.visibility = if(topSeparatorVisibility) View.VISIBLE else View.GONE
            type_name_view.text = title
            img?.let { drawable ->
                val h: Int = drawable.intrinsicHeight
                val w: Int = drawable.intrinsicWidth
                drawable.setBounds(0, 0, w, h)
            }
            type_name_view.setCompoundDrawables(img, null, null, null)
            typedArray.recycle()
        }
    }

    fun setClickListener(l: OnClickListener?) {
        parent_container.setOnClickListener(l)
    }
}