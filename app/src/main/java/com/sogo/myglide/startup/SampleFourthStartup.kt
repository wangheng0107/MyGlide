package com.sogo.myglide.startup

import android.content.Context
import android.util.Log
import com.rousetime.android_startup.AndroidStartup
import com.rousetime.android_startup.Startup

/**
 * Created by idisfkj on 2020/7/24.
 * Email: idisfkj@gmail.com.
 */
class SampleFourthStartup : AndroidStartup<Any>() {

    override fun callCreateOnMainThread(): Boolean = false

    override fun waitOnMainThread(): Boolean = false

    override fun create(context: Context): Any? {
        Thread.sleep(100)
        Log.e("xxxxx","44444");
        return null
    }

    override fun dependencies(): List<Class<out Startup<*>>>? {
        return listOf(
            SampleFirstStartup::class.java,
            SampleThirdStartup::class.java
        )
    }
}