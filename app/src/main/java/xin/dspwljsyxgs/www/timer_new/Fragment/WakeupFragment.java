package xin.dspwljsyxgs.www.timer_new.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

import xin.dspwljsyxgs.www.timer_new.MainActivity;
import xin.dspwljsyxgs.www.timer_new.R;

/**
 * Created by TYZ on 2018/3/16.
 */

public class WakeupFragment extends Fragment implements View.OnClickListener{

    private List<String> list_h = new ArrayList<String>();
    private List<String> list_m = new ArrayList<String>();
    private List<String> list_s = new ArrayList<String>();
    private ArrayAdapter<String> adapter_h;
    private ArrayAdapter<String> adapter_m;
    private ArrayAdapter<String> adapter_s;
    private TextView textView_h;
    private TextView textView_m;
    private TextView textView_s;
    private int h = 0, m = 0, s = 0 , sum;
    private Spinner sp_h;
    private Spinner sp_m;
    private Spinner sp_s;
    private Button btn1,btn2,btn3;
    private MediaPlayer mediaPlayer = new MediaPlayer();
    private  int ok=0;

    Context context = MainActivity.getInstance();
    private  int command=3;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.wakeupfragment, container, false);
        textView_h = (TextView) view.findViewById(R.id.timer_h);
        textView_m = (TextView) view.findViewById(R.id.timer_m);
        textView_s = (TextView) view.findViewById(R.id.timer_s);
        textView_h.setText("00");
        textView_m.setText("00");
        textView_s.setText("00");
        btn1=(Button) view.findViewById(R.id.timer_start);
        btn2=(Button) view.findViewById(R.id.timer_stop);
        dialog_warn();
        for (int i = 0; i <= 9; ++i) {
            for (int j = 0; j <= 9; ++j) {
                String temp;
                temp = (""+i+j);
                list_h.add(temp);
            }
        }
        for (int i = 0;i <= 5;++i){
            for (int j = 0;j <= 9;++j){
                String temp;
                temp=""+i+j;
                list_m.add(temp);
                list_s.add(temp);
            }
        }
        sp_h = (Spinner) view.findViewById(R.id.spinner_h);
        adapter_h = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, list_h);
        adapter_h.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_h.setAdapter(adapter_h);
        //设置默认值
        sp_h.setVisibility(View.VISIBLE);
        sp_h.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String now = list_h.get(i);
                h=i;
                if (i != 0) ok=1;
            }
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        sp_m = (Spinner) view.findViewById(R.id.spinner_m);
        adapter_m = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, list_m);
        adapter_m.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_m.setAdapter(adapter_m);

        //设置默认值
        sp_m.setVisibility(View.VISIBLE);
        sp_m.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String now = list_m.get(i);
                m=i;
                if (i != 0) ok=1;
            }
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        sp_s = (Spinner) view.findViewById(R.id.spinner_s);
        adapter_s = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, list_s);
        adapter_s.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_s.setAdapter(adapter_s);

        //设置默认值
        sp_s.setVisibility(View.VISIBLE);
        sp_s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String now = list_s.get(i);
                s=i;
                if (i != 0) ok=1;
            }
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);

        return view;
    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.timer_start:
                if (command != 1) {
                    sum = h * 3600 + m * 60 + s;
                    command = 1;
                    handler.postDelayed(runnable, 0);
                }
                break;
            case R.id.timer_stop:
                if (command !=3){
                    command=3;
                    handler.postDelayed(runnable,0);
                }
                break;
        }
    }
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {

        @Override

        public void run() {
            PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            boolean isScreenOn = pm.isInteractive();
            if (isScreenOn && command == 1)
            {
                h = sum / 3600;
                m = (sum - h * 3600) / 60;
                s = sum - h * 3600 - m * 60;
                textView_h.setText(list_h.get(h));
                textView_m.setText(list_m.get(m));
                textView_s.setText(list_s.get(s));
                pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
                isScreenOn = pm.isInteractive();
                handler.postDelayed(this,100);
            }
            else {
                if (sum == 0 && command == 1) {
                    if (ok == 1) dialog_text();
                    textView_h.setText("00");
                    textView_m.setText("00");
                    textView_s.setText("00");
                    sp_h.setSelection(0, true);
                    sp_m.setSelection(0, true);
                    sp_s.setSelection(0, true);
                    ok = 0;
                    command = 0;
                }

                if (command == 1) {

                    if (sum > 0) {
                        sum--;

                        h = sum / 3600;
                        m = (sum - h * 3600) / 60;
                        s = sum - h * 3600 - m * 60;
                        //textView_h.setText(list_h.get(h));
                        //textView_m.setText(list_m.get(m));
                        //textView_s.setText(list_s.get(s));
                        handler.postDelayed(this, 1000);
                    }
                }
                if (command == 3) {
                    sp_h.setSelection(0, true);
                    sp_m.setSelection(0, true);
                    sp_s.setSelection(0, true);
                    sum = 0;
                    h = m = s = 0;
                    textView_h.setText("00");
                    textView_m.setText("00");
                    textView_s.setText("00");

                }
            }
        }

    };
    public void dialog_warn(){

        new  AlertDialog.Builder(context)
                .setTitle("说明" )
                .setCancelable(false)
                .setMessage("本功能开启以后，会监测你的屏幕状态\n我们假设你睡着后手机会锁屏\n那么闹铃会在锁屏以后开始计时")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .show();
    }
    public void dialog_text(){
        initmediaplayer();
        mediaPlayer.start();
        new  AlertDialog.Builder(context)
                .setTitle("提醒" )
                .setMessage("该醒醒啦qwq" )
                .setCancelable(false)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mediastop();
                    }
                })
                .show();
    }
    public void initmediaplayer(){
        mediaPlayer.reset();
        mediaPlayer= MediaPlayer.create(context, R.raw.abc);//重新设置要播放的音频
    }

    public void mediastop(){
        mediaPlayer.stop();
        //mediaPlayer.release();
    }

}

