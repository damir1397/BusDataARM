package com.putinbyte.ambulance.utils

import android.view.Window
import android.view.WindowManager

object ScreenUtils {
    fun setKeepScreenOn(window: Window?) {
        window?.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                or WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                or WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON)
    }
}