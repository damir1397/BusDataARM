package com.putinbyte.ambulance.utils

import java.util.concurrent.Callable

interface CallableUtils : Callable<Any> {
    fun call(obj: Any): Any?
}
