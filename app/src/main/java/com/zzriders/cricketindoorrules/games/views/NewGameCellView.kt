package com.zzriders.cricketindoorrules.games.views

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.annotation.StringRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import com.zzriders.cricketindoorrules.utils.toDp

class NewGameCellView : LinearLayoutCompat {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

        orientation = VERTICAL

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.NewGameCellView)
        @StringRes val textRes = typedArray.getResourceId(R.styleable.NewGameCellView_cellText, 0)
        typedArray.recycle()

        val iconParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        iconParams.gravity = Gravity.CENTER

        val iconView = AppCompatImageView(context)
        iconView.setBackgroundResource(R.drawable.ic_rounded)
        addView(iconView, iconParams)

        val textParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        textParams.gravity = Gravity.CENTER
        textParams.topMargin = 20.toDp()

        val textView = AppCompatTextView(context)
        textView.textSize = 20f
        textView.setText(textRes)
        addView(textView, textParams)

    }
}