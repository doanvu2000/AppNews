package com.example.appnews

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner
import com.example.appnews.views.PassCodeActivity

class ApplicationUtils : Application(), Application.ActivityLifecycleCallbacks, DefaultLifecycleObserver {
    private var isPause = false

    override fun onCreate() {
        super<Application>.onCreate()
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
        registerActivityLifecycleCallbacks(this)
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        isPause = true
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        isPause = false
    }

    override fun onActivityResumed(activity: Activity) {
        if (isPause && activity !is PassCodeActivity) {
            val intent = Intent(this, PassCodeActivity::class.java)
            activity.startActivity(intent)
            isPause = false
        }
    }

    override fun onActivityCreated(p0: Activity, p1: Bundle?) = Unit
    override fun onActivityStarted(p0: Activity) = Unit

    override fun onActivityPaused(p0: Activity) = Unit
    override fun onActivityStopped(p0: Activity) = Unit

    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) = Unit

    override fun onActivityDestroyed(p0: Activity) = Unit
}