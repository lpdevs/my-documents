package com.fsoft.layouts;

import android.content.Context;
import android.content.res.Configuration;

import java.util.Locale;

/** Programmatically changes the Locale. Approach taken from
 *  http://adrianvintu.com/blogengine/post/Force-Locale-on-Android.aspx
 */

public class LocaleUtils {
    /** Sets the Locale to the given locale name (e.g., "es"). */
    
    public static void setLocale(Context context, String localeName) {
        setLocale(context, new Locale(localeName));
    }

    /** Sets the Locale to the given Locale object. */
    
    public static void setLocale(Context context, Locale locale) {
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        context.getResources().updateConfiguration(config, null);
    }
}
