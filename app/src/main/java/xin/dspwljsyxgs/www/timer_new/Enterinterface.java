package xin.dspwljsyxgs.www.timer_new;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

/**
 * This file created by dragon on 2016/6/9 11:10,belong to com.dragon.splashactivity .
 */
public class Enterinterface extends Activity implements View.OnClickListener{
    private Handler handler = new Handler();
    Button btn;
    private int time=4;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
//        NO Title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_enterinterface);
        btn=(Button)findViewById(R.id.btn1);
        btn.setOnClickListener(this);
        //延时

        handler.postDelayed(runnable,0);

    }
    public void onClick(View view){
        switch(view.getId()){
            case R.id.btn1:
                time=0;
                break;
        }
    }
    String sh="";
    Runnable runnable=new Runnable() {
        @Override
        public void run() {

            if (time > 0) {
                sh="";
                --time;
                sh+="剩余\n"+time+"s";
                btn.setText(sh);
                handler.postDelayed(this, 1000);

            }
            else {


                Intent intent = new Intent(Enterinterface.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
    };
}
