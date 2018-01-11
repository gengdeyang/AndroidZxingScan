package com.yoreach.carriersapp.pages.tabbar;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yoreach.carriersapp.R;


public class BasicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_basic); 父Activity不需要设置，会被子Activity的setContentView覆盖
        execActionBar();
    }

    /**
     * 处理ActionBar边距问题
     */
    public void execActionBar() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        ActionBar.LayoutParams lp = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT, Gravity.CENTER);
        View customView = getLayoutInflater().inflate(R.layout.actionbar, null);
        getSupportActionBar().setCustomView(customView, lp);

        Toolbar parent = (Toolbar) customView.getParent();
        parent.setPadding(0, 0, 0, 0);//for tab otherwise give space in tab
        parent.setContentInsetsAbsolute(0, 0);
    }

    /**
     * 点击ActionBar左侧返回按钮时的回调,子类可以重写
     */
    public void onActionbarLeftClick(View view) {
        finish();
    }

    /**
     * 点击ActionBar左侧返回按钮时的回调,子类可以重写
     */
    public void onActionbarRightClick(View view) {
    }

    /**
     * 设置ActionBar上的文字
     */
    public void setActionbarTitle(String title) {
        TextView tv = (TextView) findViewById(R.id.actionbar_title);
        tv.setText(title);
    }

    /**
     * 显示ActionBar右侧按钮
     */
    public void enableRightButton() {
        View view = findViewById(R.id.actionbar_right);
        view.setVisibility(View.VISIBLE);
    }

    /**
     * 更换ActionBar右侧按钮图标
     */
    public void setRightButtonRes(int resId) {
        ImageView view = (ImageView) findViewById(R.id.actionbar_right);
        view.setBackgroundResource(resId);
    }

    /**
     * 隐藏ActionBar右侧按钮
     */
    public void dismissRightButton() {
        View view = findViewById(R.id.actionbar_right);
        view.setVisibility(View.GONE);
    }

    /**
     * 显示ActionBar左侧按钮
     */
    public void enableLeftButton() {
        View view = findViewById(R.id.actionbar_left);
        view.setVisibility(View.VISIBLE);
    }

    /**
     * 更换ActionBar左侧按钮图标
     */
    public void setLeftButtonRes(int resId) {
        ImageView view = (ImageView) findViewById(R.id.actionbar_left);
        view.setBackgroundResource(resId);
    }

    /**
     * 隐藏ActionBar左侧按钮
     */
    public void dismissLeftButton() {
        View view = findViewById(R.id.actionbar_left);
        view.setVisibility(View.GONE);
    }


    public void dismissActionBar() {
        getSupportActionBar().hide();//隐藏掉整个ActionBar，包括下面的Tabs
    }

    public void enableActionBar() {
        getSupportActionBar().show();//显示ActionBar
    }
}
