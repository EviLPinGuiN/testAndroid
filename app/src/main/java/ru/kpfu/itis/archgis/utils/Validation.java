package ru.kpfu.itis.archgis.utils;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Patterns;

/**
 * Created by DNS1 on 06.05.2017.
 */

public class Validation {

    public static boolean validateFields(String name) {
        if (TextUtils.isEmpty(name)) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean validateEmail(String string) {
        if (TextUtils.isEmpty(string) || !Patterns.EMAIL_ADDRESS.matcher(string).matches()) {
            return false;
        } else {
            return true;
        }
    }



    public static boolean validatePassword(String value1, String value2) {

        if (!value1.contentEquals(value2)) {
            return false;
        } else {
            return true;
        }

    }

    public static boolean isNullOrEmpty(@Nullable String s) {
        return s == null || s.length() == 0 || s.trim().length() == 0;
    }



}
