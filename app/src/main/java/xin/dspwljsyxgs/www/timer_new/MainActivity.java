package xin.dspwljsyxgs.www.timer_new;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import xin.dspwljsyxgs.www.timer_new.Fragment.ClockFragment;
import xin.dspwljsyxgs.www.timer_new.Fragment.TimerFragment;
import xin.dspwljsyxgs.www.timer_new.Fragment.WakeupFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private final int TIMER = 1;
    private final int WAKEUP = 2;
    private final int CLOCK = 3;
    private int nowFragment = 1;
    private Button btn_timer;
    private Button btn_wakeup;
    private Button btn_clock;
    private TimerFragment timerFragment;
    private ClockFragment clockFragment;
    private WakeupFragment wakeupFragment;
    private static MainActivity instance;
    private int place=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        instance=this;
//设置监听器
        btn_timer = (Button) findViewById(R.id.timer);
        btn_timer.setOnClickListener(this);
        btn_wakeup = (Button) findViewById(R.id.wake_up);
        btn_wakeup.setOnClickListener(this);
        btn_clock = (Button) findViewById(R.id.clock);
        btn_clock.setOnClickListener(this);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.frg_right,0);
        if (timerFragment == null) {
            timerFragment = new TimerFragment();
            transaction.add(R.id.fragment_layout, timerFragment);
        } else {
            transaction.show(timerFragment);
        }
        transaction.commit();
        //!!!!!!!!!btn_timer.setImageResource(R.mipmap.click1);

       // replaceFragment(new TimerFragment());
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.timer:
                if (nowFragment == TIMER){
                    return;
                }

//                if (ButtonSlop.check(R.id.button_saishi)) {
//                    //Toast.makeText(PostActivity.this,"请稍后尝试",Toast.LENGTH_SHORT).show();
//                    return;
//                }
                showFragment(TIMER,place);
                place=1;
                //!!!!!!!!!!!!!!!!btn_timer.setImageResource(R.mipmap.click1);

                //replaceFragment(new SaishiFragment());
                break;
            case R.id.wake_up:
                if (nowFragment == WAKEUP){
                    return;
                }

//                if (ButtonSlop.check(R.id.button_saishi)) {
//                    //Toast.makeText(PostActivity.this,"请稍后尝试",Toast.LENGTH_SHORT).show();
//                    return;
//                }
                showFragment(WAKEUP,place);
                place=2;
                //!!!!!!!!!!!!!!!!btn_timer.setImageResource(R.mipmap.click1);

                //replaceFragment(new SaishiFragment());
                break;
            case R.id.clock:
                if (nowFragment == CLOCK){
                    return;
                }

//                if (ButtonSlop.check(R.id.button_saishi)) {
//                    //Toast.makeText(PostActivity.this,"请稍后尝试",Toast.LENGTH_SHORT).show();
//                    return;
//                }
                showFragment(CLOCK,place);
                place=3;
                //!!!!!!!!!!!!!!!!btn_timer.setImageResource(R.mipmap.click1);

                //replaceFragment(new SaishiFragment());
                break;

        }
    }


//    private  void replaceFragment(Fragment fragment){
//
//        FragmentManager fragmentManager=getSupportFragmentManager();
//        FragmentTransaction transaction=fragmentManager.beginTransaction();
//        transaction.replace(R.id.fragment_layout,fragment);
//        transaction.commit();
//    }



    private void showFragment(int x,int last){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragment(transaction);
        orignalImage();
        switch (x){
            case TIMER:
                if (last == 2 || last == 3){
                    transaction.setCustomAnimations(R.anim.frg_left,0);
                }
                if (timerFragment == null){
                    timerFragment = new TimerFragment();

                    transaction.add(R.id.fragment_layout, timerFragment);
                }else {

                    transaction.show(timerFragment);
                }
                break;
            case WAKEUP:
                if (last == 1){
                    transaction.setCustomAnimations(R.anim.frg_right,0);
                }
                if (last == 3){
                    transaction.setCustomAnimations(R.anim.frg_left,0);
                }
                if (wakeupFragment == null){
                    wakeupFragment = new WakeupFragment();
                    transaction.add(R.id.fragment_layout, wakeupFragment);
                }else {
                    transaction.show(wakeupFragment);
                }
                break;
            case CLOCK:
                if (last == 2 || last == 1){
                    transaction.setCustomAnimations(R.anim.frg_right,0);
                }
                if (clockFragment == null){
                    clockFragment = new ClockFragment();
                    transaction.add(R.id.fragment_layout, clockFragment);
                }else {
                    transaction.show(clockFragment);
                }
                break;
        }
        transaction.commit();
        nowFragment = x;

    }
    private void hideFragment(FragmentTransaction transaction){
        if(timerFragment!=null){
            transaction.hide(timerFragment);
        }
        if (wakeupFragment!=null){
            transaction.hide(wakeupFragment);
        }
        if (clockFragment!=null){
            transaction.hide(clockFragment);
        }
    }
    private void orignalImage(){
       //!!!!! btn_timer.setImageResource(R.mipmap.saishi);
       // !!!!!btn_wakeup.setImageResource(R.mipmap.life);
       // !!!!!btn_clock.setImageResource(R.mipmap.study);

    }
    public static MainActivity getInstance(){
        // 因为我们程序运行后，Application是首先初始化的，如果在这里不用判断instance是否为空
        return instance;
    }
    private  long lastClick = 0;
    public void onBackPressed() {
//        super.onBackPressed();
        if (lastClick <= 0) {
            Toast.makeText(this, "再按一次退出Timer", Toast.LENGTH_SHORT).show();
            lastClick = System.currentTimeMillis();
        } else {
            long currentClick = System.currentTimeMillis();
            if (currentClick - lastClick < 2000) {
                Toast.makeText(this, "期待下次再见qwq", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "再按一次退出Timer", Toast.LENGTH_SHORT).show();
                lastClick = currentClick;
            }
        }
    }

}
