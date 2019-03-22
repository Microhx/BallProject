package com.qinghua.ballproject.service;

import android.view.Gravity;
import android.view.WindowManager;
import com.qinghua.ballproject.App;
import com.qinghua.ballproject.view.FlowLayout;

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
public class BallService extends BaseService {


    @Override
    protected void showView() { {
            remove();

            mLayoutView = new FlowLayout(getApplicationContext(), mWindowManager);

            //设置LayoutParams(全局变量）相关参数
            WindowManager.LayoutParams param = App.getInstance().getWindowLayoutParams();

            param.type=WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;// 系统提示类型,重要（These windows are always on top of application windows）
            param.format=1;
            param.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;                     // 表示Window不需要获取焦点
            param.flags = param.flags | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH; // 排版限制--即允许在可见的屏幕之外
            //param.flags = param.flags | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;    //可以监听MotionEvent的ACTION_OUTSIDE事件

            param.alpha = 1.0f;

            param.gravity=Gravity.START | Gravity.TOP;   //调整悬浮窗口至左上角
            //以屏幕左上角为原点，设置x、y初始值
            param.x=0;
            param.y=0;

            //设置悬浮窗口长宽数据
            param.width = 480;
            param.height = 240;

            //显示myFloatView图像
            mWindowManager.addView(mLayoutView, param);
        }
    }
}
