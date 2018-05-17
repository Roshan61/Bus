package com.bus.bus.android;

import android.content.Context;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;

public class Publics {

    public static void setShared(Context mContext, String key, String value) {
        Hawk.init(mContext).build();
        Hawk.put(key, value);
    }

    public static String getShared(Context mContext, String key) {
        Hawk.init(mContext).build();
        if (Hawk.contains(key))
            return Hawk.get(key);
        else
            return null;
    }

    public static void deleteShared(Context mContext, String key) {
        Hawk.init(mContext).build();
        if (Hawk.contains(key))
            Hawk.delete(key);
    }

    public static void showToast(Context mContext, String txt) {
        Toast.makeText(mContext, txt, Toast.LENGTH_SHORT).show();
    }

}
