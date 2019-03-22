package com.qinghua.ballproject.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

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
public abstract class BaseService extends Service {

    private static final String TAG = BallService.class.getSimpleName();
    public static final String STOP_SERVICE = "com.qinghua.ball.stop_service";

    protected WindowManager mWindowManager;
    protected View mLayoutView;
    protected List<String> mContentList = new ArrayList<>();

    private MyBroadcastReceiver myBroadcastReceiver;
    private boolean shouldReStartService = true;

    {
        mContentList.add("新疆");
        mContentList.add("警号:092301");
        mContentList.add("2019-03-22");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG,"----onCreate----->>");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.e(TAG,"onStart ---" + System.currentTimeMillis()) ;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG,"onStartCommand:" + System.currentTimeMillis()) ;
        registerBroadcastReceiver();
        showView();

        return START_STICKY;
    }

    protected abstract void showView() ;

    protected void remove() {
        if(null != mLayoutView && mLayoutView.getParent() != null && null != mWindowManager) {
            mWindowManager.removeView(mLayoutView);
        }
    }


    @Override
    public void onDestroy() {
        Log.e(TAG,"onDestroy:" + System.currentTimeMillis());
        remove();
        unRegisterBroadcastReceiver();
        super.onDestroy();

        //再次自动重启
        if(shouldReStartService){
            startService(new Intent(this,BallService.class));
        }
    }

    private void registerBroadcastReceiver() {
        if(null == mWindowManager) {
            mWindowManager=(WindowManager)getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        }


        myBroadcastReceiver = new MyBroadcastReceiver();
        IntentFilter filter = new IntentFilter(STOP_SERVICE);
        registerReceiver(myBroadcastReceiver,filter);
    }

    private void unRegisterBroadcastReceiver() {
        if(null != myBroadcastReceiver) {
            unregisterReceiver(myBroadcastReceiver);
        }
    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private class MyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if(STOP_SERVICE.equals(intent.getAction())){
                shouldReStartService = false;
                stopSelf();
            }

        }
    }
}
