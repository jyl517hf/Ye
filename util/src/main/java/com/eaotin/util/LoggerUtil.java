package com.eaotin.util;

import android.util.Log;

/**
 * XXX.java是趣猜App的任务逻辑类。
 *
 * @author 金玉龙
 * @version 2.0 16/8/2
 * @modify
 */
public class LoggerUtil {
    private static final boolean ENABLE_LOG = true;

    private String mTag = "YE";

    public LoggerUtil(String tag) {
        mTag = tag;
    }

    public void v(String msg) {
        if (ENABLE_LOG) {
            Log.v(mTag, msg);
        }
    }

    public void d(String msg) {
        if (ENABLE_LOG) {
            Log.d(mTag, msg);
        }
    }

    public void i(String msg) {
        if (ENABLE_LOG) {
            Log.i(mTag, msg);
        }
    }

    public void w(String msg) {
        if (ENABLE_LOG) {
            Log.w(mTag, msg);
        }
    }

    public void e(String msg) {
        if (ENABLE_LOG) {
            Log.e(mTag, msg);
        }
    }
}
