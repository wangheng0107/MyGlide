package com.sogo.myglide

import android.app.Application
import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.rousetime.android_startup.StartupManager
import com.rousetime.android_startup.manager.StartupCacheManager
import com.rousetime.android_startup.model.CostTimesModel
import com.rousetime.android_startup.model.LoggerLevel
import com.rousetime.android_startup.model.StartupConfig

/**
 * Created by idisfkj on 2020/7/24.
 * Email: idisfkj@gmail.com.
 */
class SampleApplication : Application() {
    companion object {
        const val TAG = "SampleApplication"

        // only in order to test on MainActivity.
        val costTimesLiveData = MutableLiveData<List<CostTimesModel>>()
    }
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        Log.e("xxxxx", "11111");
    }

    override fun onCreate() {
        super.onCreate()
        Log.e("xxxxx", "22222");

//        val config = StartupConfig.Builder()
//            .setLoggerLevel(LoggerLevel.DEBUG)
//            .build()
//
//        StartupManager.Builder()
//            .setConfig(config)
//            .addStartup(SampleFirstStartup())
//            .addStartup(SampleThirdStartup())
//            .addStartup(SampleFourthStartup())
//            .build(this)
//            .start()
//            .await()
//        if (StartupCacheManager.instance.hadInitialized(SampleSecondStartup::class.java)) {
//            Log.d(
//                TAG,
//                "SampleSecondStartup had initialized, result => ${
//                    StartupCacheManager.instance.obtainInitializedResult<Boolean>(
//                        SampleSecondStartup::class.java
//                    )
//                }"
//            )
//        }
//
//        if (StartupCacheManager.instance.hadInitialized(SampleFourthStartup::class.java)) {
//            Log.d(
//                TAG,
//                "SampleFourthStartup had initialized, result => ${
//                    StartupCacheManager.instance.obtainInitializedResult<Boolean>(
//                        SampleFourthStartup::class.java
//                    )
//                }"
//            )
//        } else {
//            Log.e(TAG, "SampleFourthStartup not initialized.")
//        }
    }

}