package com.qinghua.ballproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.qinghua.ballproject.service.BallService;

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
public class SystemBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("SystemBroadcastReceiver","--->>" + context + "--->>" + intent);

        context.startService(new Intent(context,BallService.class));
    }
}
