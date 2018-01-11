package com.yoreach.carriersapp.pages.tabbar;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.yoreach.carriersapp.R;

@SuppressLint("NewApi")
public class MainTab04 extends Fragment {

    private TextView titlename;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_tabbar_mainview4, container, false);

        titlename = (TextView) view.findViewById(R.id.titlename);
        titlename.setText("我的");

        return view;

    }

}
