package xin.dspwljsyxgs.www.timer_new.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import xin.dspwljsyxgs.www.timer_new.R;

/**
 * Created by TYZ on 2018/3/16.
 */

public class TimerFragment extends Fragment {
    private TextView textView_h;
    private TextView textView_m;
    private TextView textView_s;
    private int h,m,s;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.timerfragment,container,false);
        textView_h=(TextView)view.findViewById(R.id.timer_h);
        textView_m=(TextView)view.findViewById(R.id.timer_m);
        textView_s=(TextView)view.findViewById(R.id.timer_s);
        h=10;
        handler.postDelayed(runnable, 1000);
        return view;
    }

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (h > 0) {
                h--;
                textView_h.setText("" + h);
                handler.postDelayed(this, 1000);
            }
        }

    };
}
