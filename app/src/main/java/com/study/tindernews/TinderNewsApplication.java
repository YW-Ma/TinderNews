package com.study.tindernews;

import android.app.Application;

import com.ashokvarma.gander.Gander;
import com.ashokvarma.gander.imdb.GanderIMDB;
import com.facebook.stetho.Stetho;

public class TinderNewsApplication extends Application {
    // 区分activity与application：
    // 离开当前应用但后台不关闭：
    //      activity状态：onStop
    //      application状态：不需要变化，它是个singleton，一个App只有一个Application的实例。

    @Override
    public void onCreate() {
        super.onCreate();
        // Gander lets you see each HTTP request from an app,
        // it’s a very convenient network debugging tool.
        Gander.setGanderStorage(GanderIMDB.getInstance());
        Stetho.initializeWithDefaults(this);
    }
}
