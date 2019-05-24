package com.zzriders.cricketindoorrules.games.views

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import androidx.annotation.StringRes
import androidx.appcompat.widget.AppCompatTextView
import com.zzriders.cricketindoorrules.R

class NewGameButton : FrameLayout {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        setBackgroundResource(R.drawable.ic_rounded)

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.NewGameButton)
        @StringRes val textRes = typedArray.getResourceId(R.styleable.NewGameButton_buttonText, 0)
        typedArray.recycle()

        val textParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        textParams.gravity = Gravity.CENTER

        val textView = AppCompatTextView(context)
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.textSize = 18f
        textView.setText(textRes)
        addView(textView, textParams)
    }
}