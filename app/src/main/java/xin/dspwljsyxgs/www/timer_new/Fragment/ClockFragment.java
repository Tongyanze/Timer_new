package xin.dspwljsyxgs.www.timer_new.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import xin.dspwljsyxgs.www.timer_new.MainActivity;
import xin.dspwljsyxgs.www.timer_new.R;

/**
 * Created by TYZ on 2018/3/16.
 */

public class ClockFragment extends Fragment implements View.OnClickListener{

    private List<String> list_h = new ArrayList<String>();
    private List<String> list_m = new ArrayList<String>();
    private List<String> list_s = new ArrayList<String>();
    private List<String> list_ms = new ArrayList<String>();
    private TextView textView_h;
    private TextView textView_m;
    private TextView textView_s;
    private TextView textView_ms;
    private TextView showresult;
    private int h = 0, m = 0, s = 0 , sum , ms=0;
    private Button btn1,btn2,btn3;
    private int state=0;
    private int preid=0;
    private MediaPlayer mediaPlayer=new MediaPlayer();
    private String result="";
    Context context = MainActivity.getInstance();
    private  int command=0;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.clockfragment, container, false);
        textView_h = (TextView) view.findViewById(R.id.timer_h);
        textView_m = (TextView) view.findViewById(R.id.timer_m);
        textView_s = (TextView) view.findViewById(R.id.timer_s);
        textView_ms=(TextView) view.findViewById(R.id.timer_ms);
        showresult=(TextView)view.findViewById(R.id.showres);
        textView_h.setText("00");
        textView_m.setText("00");
        textView_s.setText("00");
        textView_ms.setText("0");
        showresult.setText("");
        btn1=(Button) view.findViewById(R.id.clock_start);
        btn2=(Button) view.findViewById(R.id.clock_cnt);
        btn3=(Button) view.findViewById(R.id.clock_stop);
        for (int i = 0; i <= 9; ++i) {
            list_ms.add(""+i);
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

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        showresult.setMovementMethod(ScrollingMovementMethod.getInstance());
        showresult.setHorizontallyScrolling(true);
        return view;
    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.clock_start:

                    if (state == 0) {
                        btn1.setText("暂停");
                        command=1;
                        state=1;
                    }
                    else {
                        btn1.setText("开始");
                        command=2;
                        state=0;
                    }
                    handler.postDelayed(runnable, 90);

                break;
            case R.id.clock_cnt:
                result+=(++preid)+". "+list_h.get(h)+"时"+list_m.get(m)+"分"+list_s.get(s)+"秒"+list_ms.get(ms)+"\n";
                showresult.setText(result);

                break;
            case R.id.clock_stop:
                command=3;
                handler.postDelayed(runnable, 0);
                break;
        }
    }
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {

        @Override

        public void run() {
//            if (sum == 0){
//                dialog_text();
//                textView_h.setText("00");
//                textView_m.setText("00");
//                textView_s.setText("00");
//            }
            if (command == 1) {
                //if (sum > 0) {
                    h=sum/36000;
                    m=(sum-h*36000)/600;
                    s=(sum-h*36000-m*600)/10;
                    ms=sum-h*36000-m*600-s*10;
                    textView_h.setText(list_h.get(h));
                    textView_m.setText(list_m.get(m));
                    textView_s.setText(list_s.get(s));
                    textView_ms.setText(list_ms.get(ms));
                    sum++;
                    handler.postDelayed(this, 90);
                //}
            }
//            if (command == 4) {
//                //if (sum > 0) {
//                h=sum/360000;
//                m=(sum-h*360000)/6000;
//                s=(sum-h*360000-m*6000)/100;
//                ms=sum-h*360000-m*6000-s*100;
//                result+=(++preid)+". "+list_h.get(h)+"时"+list_m.get(m)+"分"+list_s.get(s)+"秒"+list_ms.get(ms)+"\n";
//
//                showresult.setText(result);
//                sum++;
//                handler.postDelayed(this, 100);
//                //}
//            }
            if (command == 3){
                result="";
                btn1.setText("开始");
                state=0;
                h=m=s=ms=0;
                preid=0;
                textView_h.setText("00");
                textView_m.setText("00");
                textView_s.setText("00");
                textView_ms.setText("0");
                result="";
                showresult.setText(result);
                sum=0;
            }
        }

    };

    public void dialog_text(){
        initmediaplayer();
        mediaPlayer.start();
        new  AlertDialog.Builder(context)
                .setTitle("提醒" )
                .setMessage("时间到了哦" )
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
        mediaPlayer.release();
    }

}

