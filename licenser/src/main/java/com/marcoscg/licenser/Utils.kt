package com.marcoscg.licenser

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.util.TypedValue
import androidx.annotation.ColorInt
import kotlin.math.min
import kotlin.math.roundToInt

/**
 * Created by @MarcosCGdev on 11/02/2018.
 */
object Utils {

    fun getThemeColor(context: Context, attr: Int): Int {
        val typedValue = TypedValue()

        return if (context.theme.resolveAttribute(attr, typedValue, true)) {
            typedValue.data
        } else Color.WHITE
    }

    fun colorHex(@ColorInt color: Int): String {
        return String.format("#%06X", 0xFFFFFF and color)
    }

    fun dpToPx(dp: Int): Int {
        return (dp * Resources.getSystem().displayMetrics.density).toInt()
    }

    fun isColorLight(@ColorInt color: Int): Boolean {
        if (color == Color.BLACK) return false else if (color == Color.WHITE || color == Color.TRANSPARENT) return true
        val darkness = 1 - (0.299 * Color.red(color) + 0.587 * Color.green(color) + 0.114 * Color.blue(color)) / 255

        return darkness < 0.4
    }

    fun darkenColor(@ColorInt color: Int): Int {
        val hsv = FloatArray(3)
        Color.colorToHSV(color, hsv)
        hsv[2] *= 0.8f

        return Color.HSVToColor(hsv)
    }

    fun lightenColor(@ColorInt color: Int): Int {
        val fraction = 0.15f
        val alpha = Color.alpha(color)
        val red = min(255f, Color.red(color) + 255 * fraction).roundToInt()
        val green = min(255f, Color.green(color) + 255 * fraction).roundToInt()
        val blue = min(255f, Color.blue(color) + 255 * fraction).roundToInt()

        return Color.argb(alpha, red, green, blue)
    }

}