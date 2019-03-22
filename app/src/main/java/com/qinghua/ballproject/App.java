package com.qinghua.ballproject;

import android.app.Application;
import android.view.WindowManager;

/**
 * created by Java
 * <p>
 * 纵然万劫不复，纵然相思入骨，我依然待你眉眼如初，岁月如故。
 * <p>
 * date : 2019/3/22
 * <p>
 * version :
 * <p>
 * desc :
 */
public class App extends Application {

    private WindowManager.LayoutParams mWindowLayoutParams = new WindowManager.LayoutParams();

    private static App instance ;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static App getInstance() {
        return instance;
    }

    public WindowManager.LayoutParams getWindowLayoutParams() {
        return mWindowLayoutParams;
    }
}
