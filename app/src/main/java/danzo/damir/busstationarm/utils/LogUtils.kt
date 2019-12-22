package com.putinbyte.ambulance.utils

import android.util.Log
import com.blankj.utilcode.util.TimeUtils

object LogUtils {
    fun info(tag: String, info: String?) {
        Log.i(tag, "[${TimeUtils.getNowString()}] $info")
    }

    fun error(tag: String, error: String?) {
        Log.e(tag, "[${TimeUtils.getNowString()}] $error")
    }

    fun error(tag: String, error: String?, e: Exception?) {
        Log.e(tag, "[${TimeUtils.getNowString()}] $error", e)
    }

    fun warning(tag: String, warning: String?) {
        Log.w(tag, "[${TimeUtils.getNowString()}] $warning")
    }
}