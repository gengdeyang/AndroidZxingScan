/*
 * 未配载订单页面
 * Created by 宇 on 2017-12-24
 */
package com.yoreach.carriersapp.pages.tabbar;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.yoreach.carriersapp.R;
import com.yoreach.carriersapp.component.MyListView;
import com.yoreach.carriersapp.component.MyListView.OnRefreshListener;
import com.yoreach.carriersapp.bean.UndoneTaskBean;
import com.yoreach.carriersapp.adapter.MyListAdapter;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressLint("NewApi")
public class NewTask extends Fragment implements OnRefreshListener {

    private MyListView listView;
    private MyListAdapter mAdapter;
    private TextView mSelectedCount, total_all, total_car, total_suv;
    private Button total_but;
    private List<UndoneTaskBean> mdata = null;
    private JSONObject inf ;
    private List<UndoneTaskBean> arraylist ;
    private int totalCarNum = 0,totalSuvNum = 0,totalNum = 0 ;
    private ArrayList<UndoneTaskBean> taskSecletedArrayList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_task_page01_content, container, false);
        listView = (MyListView) view.findViewById(R.id.MyListView);
        listView.setDividerHeight(1);


        total_all = (TextView) view.findViewById(R.id.total_all);
        total_car = (TextView) view.findViewById(R.id.total_car);
        total_suv = (TextView) view.findViewById(R.id.total_suv);
        total_but = (Button) view.findViewById(R.id.total_but);

        total_but.setOnClickListener(new Button.OnClickListener(){//创建监听
            public void onClick(View v) {
                taskSecletedArrayList = new ArrayList<UndoneTaskBean>();

                for (int i = 0; i < arraylist.size(); i++) {

                    if (listView.isItemChecked(i+1)) {  //选中
                        Log.i("选中=", i+"");
                        UndoneTaskBean task = arraylist.get(i);
                        taskSecletedArrayList.add(task );
                    }
                }

                //新建一个显式意图，第一个参数为当前Activity类对象，第二个参数为你要打开的Activity类
                Intent intent = new Intent();
                intent.setClass(getActivity(), CarrierMatchActivity.class);
                intent.putExtra("taskinfo", (Serializable)taskSecletedArrayList);
                intent.putExtra("totalcarnum", totalNum);
                startActivity(intent);
            }
        });

        listView.setEmptyView(view.findViewById(R.id.layout_empty));

        ArrayList<UndoneTaskBean> mdata = new ArrayList<>();

        mAdapter = new MyListAdapter( mdata, getActivity(), listView);
        listView.setAdapter(mAdapter);

        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position,   //position从1开始
                                    long id) {
                mAdapter.notifyDataSetChanged();

                    UndoneTaskBean inf_Array = arraylist.get(position-1 );  //index从0开始

                    if (listView.isItemChecked(position)) {  //选中
                        totalCarNum = totalCarNum +  Integer.parseInt(inf_Array.getCarNum() );
                        totalSuvNum = totalSuvNum +  Integer.parseInt(inf_Array.getSuvNum() );

                        totalNum = totalCarNum +totalSuvNum;

                        updateSeletedCount();

                        Log.i("position1=",""+position);
                        Log.i("totalCarNum=",""+totalCarNum);
                        Log.i("totalSuvNum=",""+totalSuvNum);


                    } else {
                        totalCarNum = totalCarNum +  Integer.parseInt(inf_Array.getCarNum() );
                        totalSuvNum = totalSuvNum +  Integer.parseInt(inf_Array.getSuvNum() );

                        totalNum = totalCarNum + totalSuvNum;
                        Log.i("position2=",""+position);
                        Log.i("totalCarNum=",""+totalCarNum);
                        Log.i("totalSuvNum=",""+totalSuvNum);

                        updateSeletedCount();

                    }

            }
        });

            arraylist = new ArrayList<UndoneTaskBean>();

            for (int i = 0; i < 5; i++) {
                UndoneTaskBean arr = new UndoneTaskBean();
                arr.setArrivedTime("10-21" );
                arr.setCarNum( "2");
                arr.setShopName("邯郸嘉华4S店");
                arr.setSuvNum(i+"");
                arr.setYundanNO( "20171022****727827");

//                JSONObject arr_1 = new JSONObject();
//                arr_1.put("arrivedTime", "10-21");
//                arr_1.put("yundanNO", "20171022****727827");
//                arr_1.put("shopName", "邯郸嘉华4S店");
//                arr_1.put("carNum", "2");
//                arr_1.put("suvNum", i);
//                arraylist.add( arr);
                arraylist.add(i, arr);
            }
           Log.i("arraylistsize=",""+arraylist.size());

            for (int i = 0; i < arraylist.size(); i++) {
                UndoneTaskBean inf_Array = arraylist.get(i);
                mAdapter.add(new UndoneTaskBean(inf_Array.getArrivedTime(), inf_Array.getYundanNO(), inf_Array.getShopName(), inf_Array.getCarNum(), inf_Array.getSuvNum()));

            }




        mAdapter.notifyDataSetChanged();

        //设置回调
        listView.setOnRefreshListener(this);
        listView.refreshHeaderView();


        return view;
    }

    /*
     * 更改购物车数量
     */
    public void updateSeletedCount() {

        total_all.setText("总计："+totalNum+"台");
        total_car.setText("CAR："+totalCarNum+"台");
        total_suv.setText("CAR："+totalSuvNum+"台");
    }


//    public void updateCartData() {
//        Toast.makeText(getActivity(), Integer.toString(listView.getCheckedItemCount()),
//                Toast.LENGTH_SHORT).show();
//    }
//    public void selectedAll() {
//        for (int i = 0; i < mAdapter.getCount(); i++) {
//            listView.setItemChecked(i, true);
//        }
//        updateSeletedCount();
//    }
//    public void unSelectedAll() {
//        listView.clearChoices();
//        updateSeletedCount();
//    }


    @Override
    public void onDownPullRefresh() {
        // TODO Auto-generated method stub
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                SystemClock.sleep(2000);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                mAdapter.notifyDataSetChanged();
                listView.hideHeaderView();

            }
        }.execute(new Void[]{});
    }

    @Override
    public void onLoadingMore() {
        // TODO Auto-generated method stub
        // 加载更多
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                SystemClock.sleep(2000);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                mAdapter.notifyDataSetChanged();
                listView.hideFooterView();

            }
        }.execute(new Void[]{});


    }

}