/*
 * 轿运车匹配
 * Created by 宇 on 2017-12-24
 */
package com.yoreach.carriersapp.pages.tabbar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.client.android.CaptureActivity2;
import com.yoreach.carriersapp.R;
import com.yoreach.carriersapp.ScanResultActivity;
import com.yoreach.carriersapp.WebViewActivity;
import com.yoreach.carriersapp.bean.UndoneTaskBean;

import java.util.ArrayList;

@SuppressLint("NewApi")
public class CarrierMatchActivity extends BasicActivity {
    private ArrayList<UndoneTaskBean> arrayList;  //选中的车辆汇总
    private int totalcarnum;  //车辆总数
    private String scanResult;
    private static final int SCAN_REQUEST_CODE = 100;
    private static final int CAMERA_PERMISSION = 110;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_matchcarriercar);
        setActionbarTitle("车辆匹配");

        Intent intent = getIntent();
        arrayList = (ArrayList<UndoneTaskBean>) intent.getSerializableExtra("taskinfo");
        totalcarnum = (int) intent.getIntExtra("totalcarnum",0);
        addOne();
        //扫描司机二维码
        findViewById(R.id.ercodebut).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT > 22) {
                    if (ContextCompat.checkSelfPermission(CarrierMatchActivity.this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(CarrierMatchActivity.this, new String[]{android.Manifest.permission.CAMERA}, CAMERA_PERMISSION);
                    } else {
                        startScanActivity();
                    }
                } else {
                    startScanActivity();
                }
            }
        });

        //提交按钮
        findViewById(R.id.button_tijiao).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT > 22) {
                    if (ContextCompat.checkSelfPermission(CarrierMatchActivity.this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(CarrierMatchActivity.this, new String[]{android.Manifest.permission.CAMERA}, CAMERA_PERMISSION);
                    } else {
                        startScanActivity();
                    }
                } else {
                    startScanActivity();
                }
            }
        });
    }

    private void startScanActivity() {
        Intent intent = new Intent(CarrierMatchActivity.this, CaptureActivity2.class);
        intent.putExtra(CaptureActivity2.USE_DEFUALT_ISBN_ACTIVITY, true);
        startActivityForResult(intent, SCAN_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case CAMERA_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startScanActivity();
                } else {
                    Toast.makeText(CarrierMatchActivity.this, "请手动打开摄像头权限", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public int checkSelfPermission(String permission) {
        return super.checkSelfPermission(permission);
    }

    private void addOne() {
        Log.i("size=", arrayList.size() + "");
        final LinearLayout layout = (LinearLayout) findViewById(R.id.taskinfo);
        for (int i = 0; i < arrayList.size(); i++) {

            UndoneTaskBean task = arrayList.get(i);
            View inflate = getLayoutInflater().inflate(R.layout.activity_undotask_listitem, null);
            TextView text01 = (TextView) inflate.findViewById(R.id.item_text01);
            TextView text02 = (TextView) inflate.findViewById(R.id.item_text02);
            TextView text03 = (TextView) inflate.findViewById(R.id.item_text03);
            TextView text04 = (TextView) inflate.findViewById(R.id.item_text04);
            TextView text05 = (TextView) inflate.findViewById(R.id.item_text05);
            text01.setText(task.getArrivedTime());
            text02.setText(task.getYundanNO());
            text03.setText(task.getShopName());
            text04.setText(task.getCarNum());
            text05.setText(task.getSuvNum());
            layout.addView(inflate);

            Log.i("iii=", i + task.getArrivedTime());
        }
        TextView carnumtext =  (TextView) findViewById(R.id.textView182);
        carnumtext.setText(totalcarnum+"");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SCAN_REQUEST_CODE) {
                //Todo Handle the isbn number entered manually
                scanResult = data.getStringExtra("CaptureIsbn");
                if (!TextUtils.isEmpty(scanResult)) {
                    //请求司机信息接口
                    Toast.makeText(this, "解析到的内容为" + scanResult, Toast.LENGTH_LONG).show();


                }else{
                    Toast.makeText(this, "扫描到的内容为空" , Toast.LENGTH_LONG).show();

                }
            }
        }
    }

}