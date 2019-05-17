package com.zzriders.cricketindoorrules.games.views

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.annotation.StringRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.zzriders.cricketindoorrules.R
import com.zzriders.cricketindoorrules.utils.toPx

class CounterView : LinearLayout {
    interface CounterListener {
        fun onIncrementClicked()
        fun onDecrementClicked()
    }

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    var counterListener: CounterListener? = null
    private fun init(context: Context, attrs: AttributeSet?) {
        orientation = VERTICAL

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CounterView)
        @StringRes val textRes = typedArray.getResourceId(R.styleable.CounterView_title, 0)
        typedArray.recycle()

        addTitleView(context, textRes)
        addIndicatorView(context)
        addActionViews(context)
    }

    private fun addTitleView(context: Context, @StringRes title: Int) {
        val params = LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        params.gravity = Gravity.CENTER_HORIZONTAL
        params.topMargin = 10.toPx()

        val title = AppCompatTextView(context)
//        title.setText(title)
        title.isAllCaps = true
        addView(title, params)
    }

    private lateinit var indicator: AppCompatTextView

    private fun addIndicatorView(context: Context) {
        val params = LayoutParams(100.toPx(), 100.toPx())
        params.gravity = Gravity.CENTER_HORIZONTAL

        indicator = AppCompatTextView(context)
//        title.setText(title)
        indicator.isAllCaps = true
        addView(indicator, params)
    }

    private fun addActionViews(context: Context) {
        val containerParams = LayoutParams(100.toPx(), 100.toPx())
        containerParams.topMargin = 20.toPx()

        val container = RelativeLayout(context)
        addView(container, containerParams)

        addIncrementView(context, container)
        addDecrementView(context, container)
    }

    private fun addIncrementView(context: Context, container: RelativeLayout) {
        val params = RelativeLayout.LayoutParams(48.toPx(), 48.toPx())
        params.addRule(RelativeLayout.ALIGN_PARENT_START)

        val view = AppCompatImageView(context)
        view.setImageResource(R.drawable.ic_increment)
        view.setOnClickListener{counterListener?.onIncrementClicked()}

        container.addView(view, params)
    }

    private lateinit var decrementView: AppCompatImageView
    private fun addDecrementView(context: Context, container: RelativeLayout) {
        val params = RelativeLayout.LayoutParams(48.toPx(), 48.toPx())
        params.addRule(RelativeLayout.ALIGN_PARENT_END)

        decrementView = AppCompatImageView(context)
        decrementView.setImageResource(R.drawable.ic_decrement)
        decrementView.setOnClickListener{counterListener?.onDecrementClicked()}

        container.addView(decrementView, params)
    }

    fun disableDecrement(disable: Boolean) {
        decrementView.isEnabled = !disable
    }

    fun setIndicatorValue(value: String) {
        indicator.text = value
    }
}