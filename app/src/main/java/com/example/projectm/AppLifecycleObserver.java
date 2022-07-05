/*

package com.example.projectm;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public class MyApplication extends Application
        implements ActivityLifecycleCallbacks, Runnable
{
    private Handler h;

    @Override public void onCreate()
    {
        h = new Handler();
        registerActivityLifecycleCallbacks(this);
    }

    public void onActivityCreated(Activity activity, Bundle savedInstanceState) { }
    public void onActivityStarted(Activity activity) { }
    public void onActivityStopped(Activity activity) { }
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) { }
    public void onActivityDestroyed(Activity activity) { }

    public void onActivityResumed(Activity activity)
    {
        h.removeCallbacks(this);
        startService(new Intent(this, Meni_music.class));
    }

    public void onActivityPaused(Activity activity);
    {
        h.postDelayed(this, 500);
    }

    public void run()
    {
        stopService(new Intent(this, Meni_music.class));
    }
}*/
