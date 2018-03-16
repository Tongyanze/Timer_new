package xin.dspwljsyxgs.www.timer_new.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import xin.dspwljsyxgs.www.timer_new.R;

/**
 * Created by TYZ on 2018/3/16.
 */

public class ClockFragment extends Fragment {
    private TextView textView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.clockfragment,container,false);

        textView=(TextView)view.findViewById(R.id.clock_h);


        return view;
    }
}
