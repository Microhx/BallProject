package com.qinghua.ballproject

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.RadioGroup
import android.widget.Toast
import com.qinghua.ballproject.service.BallService
import com.qinghua.ballproject.service.WaterService

class MainActivity : AppCompatActivity(), RadioGroup.OnCheckedChangeListener {

    private var mIndex:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<RadioGroup>(R.id.id_rg).setOnCheckedChangeListener(this)
    }

    fun openBall(view: View) {
        showBall()
    }


    private fun showBall() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (!Settings.canDrawOverlays(this)) {
                val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:$packageName"))
                startActivityForResult(intent, 200)
                return
            }
        }

        if(mIndex == 0) {
            startService(Intent(this, BallService::class.java))
        }else {
            startService(Intent(this, WaterService::class.java))
        }

    }


    fun closeBall(view :View) {
        sendBroadcast(Intent(BallService.STOP_SERVICE))
        showCloseInfo()
    }

    private fun showCloseInfo() {
        showInfo("close success!!")
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 200 && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(!Settings.canDrawOverlays(MainActivity@this)) {
                showInfo("please open the settings, otherwise you can not this!!!")
            }
        }
    }

    private fun showInfo(info:String) {
        Toast.makeText(applicationContext,info,Toast.LENGTH_LONG).show()
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        mIndex = if(checkedId == R.id.id_rb_water) {
            1
        }else{
            0
        }
    }

}
