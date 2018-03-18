package xin.dspwljsyxgs.www.timer_new;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

/**
 * This file created by dragon on 2016/6/9 11:10,belong to com.dragon.splashactivity .
 */
public class Enterinterface extends Activity {
    private Handler handler = new Handler();

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
//        NO Title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_enterinterface);
//延时
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Enterinterface.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);

    }
}
