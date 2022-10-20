package com.simonren.workflow

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper

/**
 * @author Simon
 * @date 2022/10/8
 * @time 17:58
 * @desc
 */
class WorkApp : Application(), Application.ActivityLifecycleCallbacks {

    internal class InternalHandler(looper: Looper) : Handler(looper)

    companion object {
        private var mHandler: Handler? = null
        private fun getMainHandler(): Handler {
            synchronized(WorkApp::class.java) {
                if (mHandler == null) {
                    mHandler = InternalHandler(Looper.getMainLooper())
                }
                return mHandler!!
            }
        }

        var mContext: Context? = null
        var topActivity: Activity? = null

        @JvmStatic
        fun mainThreadExecute(uiTask: Runnable) {
            getMainHandler().post(uiTask)
        }
    }


    override fun onCreate() {
        super.onCreate()
        mContext = this
        registerActivityLifecycleCallbacks(this)
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        topActivity = activity
    }

    override fun onActivityStarted(activity: Activity) {
    }

    override fun onActivityResumed(activity: Activity) {

    }

    override fun onActivityPaused(activity: Activity) {

    }

    override fun onActivityStopped(activity: Activity) {

    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
    }

    override fun onActivityDestroyed(activity: Activity) {
        topActivity = null
    }
}