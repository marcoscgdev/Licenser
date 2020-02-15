package com.marcoscg.licenser;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import androidx.annotation.ColorInt;
import android.util.TypedValue;

/**
 * Created by @MarcosCGdev on 11/02/2018.
 */

public class Utils {

    static int getThemeColor(Context context, int attr) {
        TypedValue typedValue = new TypedValue();

        if (context.getTheme().resolveAttribute(attr, typedValue, true)) {
            return typedValue.data;
        }

        return Color.WHITE;
    }

    static String colorHex(@ColorInt int color) {
        return String.format("#%06X", (0xFFFFFF & color));
    }

    static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    static boolean isColorLight(@ColorInt int color) {
        if (color == Color.BLACK) return false;
        else if (color == Color.WHITE || color == Color.TRANSPARENT) return true;

        final double darkness = 1 - (0.299 * Color.red(color) + 0.587 * Color.green(color) + 0.114 * Color.blue(color)) / 255;

        return darkness < 0.4;
    }

    static int darkenColor(@ColorInt int color) {
        float[] hsv = new float[3];

        Color.colorToHSV(color, hsv);
        hsv[2] *= 0.8f;

        return Color.HSVToColor(hsv);
    }

    static int lightenColor(@ColorInt int color) {
        float fraction = 0.15f;

        int alpha = Color.alpha(color);
        int red = Math.round(Math.min(255, Color.red(color) + 255 * fraction));
        int green = Math.round(Math.min(255, Color.green(color) + 255 * fraction));
        int blue = Math.round(Math.min(255, Color.blue(color) + 255 * fraction));

        return Color.argb(alpha, red, green, blue);
    }

}
