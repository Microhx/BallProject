package com.qinghua.ballproject.view;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import com.qinghua.ballproject.App;
import com.qinghua.ballproject.R;

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
public class FlowLayout extends ConstraintLayout {

    private WindowManager mWindowManger;
    private WindowManager.LayoutParams mLayoutParams;
    private TextView mTvProvince;
    private TextView mTvNumber;
    private TextView mTvDate ;

    private float mTouchStartX ;
    private float mTouchStartY ;
    private float x;
    private float y;


    public FlowLayout(Context context, WindowManager windowManager) {
        super(context, null);
        this.mWindowManger = windowManager;
        mLayoutParams = App.getInstance().getWindowLayoutParams();

        initViews();
    }

    private void initViews() {
        View rootView  = View.inflate(getContext(),R.layout.item_layout,this);
        mTvDate = rootView.findViewById(R.id.id_tv_date);
        mTvNumber = rootView.findViewById(R.id.id_tv_number);
        mTvProvince = rootView.findViewById(R.id.id_tv_province);
    }

    public void updateContent(String province , String number , String date) {
        mTvProvince.setText(province);
        mTvNumber.setText(number);
        mTvDate.setText(date);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x = event.getRawX();
        y = event.getRawY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mTouchStartX = event.getX();
                mTouchStartY = event.getY();
                break;

            case MotionEvent.ACTION_MOVE:
                updateViewPosition();
                break;

            case MotionEvent.ACTION_UP:
                mTouchStartX = mTouchStartY = 0 ;

                break;
        }

        return true;
    }

    private void updateViewPosition() {
        mLayoutParams.x = (int) (x - mTouchStartX);
        mLayoutParams.y = (int) (y - mTouchStartY);

        mWindowManger.updateViewLayout(this,mLayoutParams);
    }
}
