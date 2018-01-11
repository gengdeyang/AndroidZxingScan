/*
 * 已配载订单页面
 * Created by 宇 on 2017-12-24
 */
package com.yoreach.carriersapp.pages.tabbar;

import android.annotation.SuppressLint;
import android.app.Fragment;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("NewApi")
public class ArrangedTaskActivity extends Fragment implements OnRefreshListener {

    private MyListView listView;
    private MyListAdapter mAdapter;
    private TextView mSelectedCount, total_all, total_car, total_suv;
    private Button total_but;
    private List<UndoneTaskBean> mdata = null;
    private JSONObject inf ;
    private JSONArray array ;
    private int totalCarNum = 0,totalSuvNum = 0,totalNum = 0 ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_task_page01_content, container, false);
        listView = (MyListView) view.findViewById(R.id.MyListView);
        listView.setDividerHeight(1);


        total_all = (TextView) view.findViewById(R.id.total_all);
        total_car = (TextView) view.findViewById(R.id.total_car);
        total_suv = (TextView) view.findViewById(R.id.total_suv);
        total_but = (Button) view.findViewById(R.id.total_but);

        listView.setEmptyView(view.findViewById(R.id.layout_empty));

        mAdapter = new MyListAdapter((ArrayList<UndoneTaskBean>) mdata, getActivity(), listView);
        listView.setAdapter(mAdapter);

        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                mAdapter.notifyDataSetChanged();
                try {
                    JSONObject inf_Array = array.getJSONObject(position-1);

                    if (listView.isItemChecked(position)) {  //选中
                        Log.i("position=",""+position);
                        totalCarNum = totalCarNum +  Integer.parseInt(inf_Array.getString("carNum") );
                        totalSuvNum = totalSuvNum +  Integer.parseInt(inf_Array.getString("suvNum") );

                        totalNum = totalCarNum +totalSuvNum;

                        updateSeletedCount();

                        Log.i("position1=",""+position);
                        Log.i("totalCarNum=",""+totalCarNum);
                        Log.i("totalSuvNum=",""+totalSuvNum);

                    } else {
                        totalCarNum = totalCarNum -  Integer.parseInt(inf_Array.getString("carNum") );
                        totalSuvNum = totalSuvNum -  Integer.parseInt(inf_Array.getString("suvNum") );

                        totalNum = totalCarNum + totalSuvNum;
                        Log.i("position2=",""+position);
                        Log.i("totalCarNum=",""+totalCarNum);
                        Log.i("totalSuvNum=",""+totalSuvNum);

                        updateSeletedCount();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }




            }
        });

        try {
            inf = new JSONObject();

            array = new JSONArray();
            for (int i = 0; i < 20; i++) {
                JSONObject arr_1 = new JSONObject();
                arr_1.put("arrivedTime", "10-21");
                arr_1.put("yundanNO", "20171022****727827");
                arr_1.put("shopName", "邯郸嘉华4S店");
                arr_1.put("carNum", "2");
                arr_1.put("suvNum", i);

                array.put(i, arr_1);
            }
            inf.put("inf", array);


            for (int i = 0; i < array.length(); i++) {
                JSONObject inf_Array = array.getJSONObject(i);
                mAdapter.add(new UndoneTaskBean(inf_Array.getString("arrivedTime"), inf_Array.getString("yundanNO"), inf_Array.getString("shopName"), inf_Array.getString("carNum"), inf_Array.getString("suvNum")));

            }

        } catch (JSONException e) {
            e.printStackTrace();
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