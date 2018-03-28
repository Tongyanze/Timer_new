package xin.dspwljsyxgs.www.timer_new.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
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
import java.util.Calendar;
import java.util.List;

import xin.dspwljsyxgs.www.timer_new.MainActivity;
import xin.dspwljsyxgs.www.timer_new.R;

/**
 * Created by TYZ on 2018/3/16.
 */

public class ClockFragment extends Fragment implements View.OnClickListener{

    private List<String> list_h = new ArrayList<String>();
    private List<String> list_m = new ArrayList<String>();
    //private List<String> list_s = new ArrayList<String>();
    private ArrayAdapter<String> adapter_h;
    private ArrayAdapter<String> adapter_m;
    //private ArrayAdapter<String> adapter_s;
    private Spinner sp_h;
    private Spinner sp_m;
    //private Spinner sp_s;
    private int ok=0;
    //private List<String> list_ms = new ArrayList<String>();
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
        ///textView_s = (TextView) view.findViewById(R.id.timer_s);
        //textView_ms=(TextView) view.findViewById(R.id.timer_ms);
        //showresult=(TextView)view.findViewById(R.id.showres);
        textView_h.setText("00");
        textView_m.setText("00");
        //textView_s.setText("00");
       // textView_ms.setText("0");
        //showresult.setText("");
        btn1=(Button) view.findViewById(R.id.clock_start);
        //btn2=(Button) view.findViewById(R.id.clock_cnt);
        btn3=(Button) view.findViewById(R.id.clock_stop);
        for (int i = 0; i <= 1; ++i) {
            for (int j = 0; j <= 9; ++j) {
                String temp;
                temp = (""+i+j);
                list_h.add(temp);
            }
        }
        for (int i = 0;i <= 3;++i){
            String temp;
            temp=""+2+i;
            list_h.add(temp);
        }
        for (int i = 0;i <= 5;++i){
            for (int j = 0;j <= 9;++j){
                String temp;
                temp=""+i+j;
                list_m.add(temp);
                //list_s.add(temp);
            }
        }
        sp_h = (Spinner) view.findViewById(R.id.spinner_h);
        sp_m = (Spinner) view.findViewById(R.id.spinner_m);


        settime();
        btn1.setOnClickListener(this);
        //btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        //showresult.setMovementMethod(ScrollingMovementMethod.getInstance());
        //showresult.setHorizontallyScrolling(true);
        return view;
    }
    public void settime(){

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
                textView_h.setText(now);
                if (i != 0) ok=1;
            }
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });


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
                textView_m.setText(now);
                if (i != 0) ok=1;
            }
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        /*sp_s = (Spinner) view.findViewById(R.id.spinner_s);
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
        });*/
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.clock_start:

                    //if (state == 0) {
                     //   btn1.setText("暂停");
                     //   command=1;
                     //   state=1;
                    //}
                    //else {
                    if (command != 1) {
                        btn1.setText("开始");
                        command = 1;
                        state = 0;
                        Toast.makeText(context,"设置成功，闹铃将在"+list_h.get(h)+"时"+list_m.get(m)+"分响起",Toast.LENGTH_SHORT).show();
                        handler.postDelayed(runnable, 0);
                    }
                    else {
                        Toast.makeText(context,"你已经设置过闹钟了哦",Toast.LENGTH_SHORT).show();
                    }

                   // }


                break;
            /*case R.id.clock_cnt:
                result+=(++preid)+". "+list_h.get(h)+"时"+list_m.get(m)+"分"+list_s.get(s)+"秒"+"\n";
                showresult.setText(result);

                break;*/
            case R.id.clock_stop:
                command=3;
                Toast.makeText(context,"闹钟已关闭",Toast.LENGTH_SHORT).show();
                handler.postDelayed(runnable, 0);
                break;
        }
    }
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {

        @Override

        public void run() {

                if (command == 1) {

                    Calendar c=Calendar.getInstance();
                    int nh=c.get(Calendar.HOUR_OF_DAY);
                    int nm=c.get(Calendar.MINUTE);
                    if (h == nh && m == nm) {
                        if (ok == 1) dialog_text();
                        //textView_h.setText("00");
                        //textView_m.setText("00");
                        //textView_s.setText("00");
                        sp_h.setSelection(0, true);
                        sp_m.setSelection(0, true);
                        //sp_s.setSelection(0, true);
                        ok = 0;
                        command = 0;
                    }
                    else{
                        handler.postDelayed(this, 1000);
                    }
                }
                if (command == 3) {
                    sp_h.setSelection(0, true);
                    sp_m.setSelection(0, true);
                    //sp_s.setSelection(0, true);
                    sum = 0;
                    h = m = s = 0;
                    ok=0;
                    //textView_h.setText("00");
                    //textView_m.setText("00");
                    //textView_s.setText("00");

                }
        }

    };

    public void dialog_text(){
        initmediaplayer();
        mediaPlayer.start();
        new  AlertDialog.Builder(context)
                .setTitle("提醒" )
                .setMessage("点击下方按钮关闭闹铃")
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

