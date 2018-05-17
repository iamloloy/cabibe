package com.cabibe;

import android.app.Activity;
import android.app.Application;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.multidex.MultiDex;

import com.cabibe.utils.TypeFaceUtil;

/**
 * Created by ekxia on 5/2/2018.
 */

public class CabibeApplication
    extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP)
            MultiDex.install(this);

        TypeFaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/BebasNeue-Regular.tff");
        registerActivityLifecycleCallbacks(
                new ActivityLifecycleCallbacks() {
                    @Override
                    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                        activity.setRequestedOrientation(
                                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    }

                    @Override
                    public void onActivityStarted(Activity activity) {

                    }

                    @Override
                    public void onActivityResumed(Activity activity) {

                    }

                    @Override
                    public void onActivityPaused(Activity activity) {

                    }

                    @Override
                    public void onActivityStopped(Activity activity) {

                    }

                    @Override
                    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

                    }

                    @Override
                    public void onActivityDestroyed(Activity activity) {

                    }
                }
        );
    }
}
