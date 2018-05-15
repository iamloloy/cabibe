package com.cabibe.utils;

import android.os.Build;
import android.text.Html;
import android.text.Spanned;

/**
 * Created by ekxia on 5/2/2018.
 */

public class HtmlUtils {
    public static Spanned fromHtml(String source) {
        if (Build.VERSION.SDK_INT >= 24) {
            return Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(source);
        }
    }
}
