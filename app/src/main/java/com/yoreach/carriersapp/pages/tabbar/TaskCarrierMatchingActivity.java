package com.yoreach.carriersapp.pages.tabbar;

import android.os.Bundle;
import android.widget.GridLayout;

import com.yoreach.carriersapp.bean.UndoneTaskBean;

import java.util.ArrayList;

public class TaskCarrierMatchingActivity extends BasicActivity {


    private GridLayout lostslayout;
    private boolean isShowLosts;
    private ArrayList<UndoneTaskBean> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView();
        setActionbarTitle("质损报告");

        arrayList = (ArrayList<UndoneTaskBean>) getIntent().getSerializableExtra("key");
        String result = "" ;
        for (UndoneTaskBean myClass2 : arrayList) {

        }

        addOne();

    }


    private void addOne() {

    }

}
