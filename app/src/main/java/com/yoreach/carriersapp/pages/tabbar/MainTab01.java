package com.yoreach.carriersapp.pages.tabbar;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.yoreach.carriersapp.R;
import com.yoreach.carriersapp.component.FilterPopupWindow;

import java.util.ArrayList;
import java.util.List;


@SuppressLint("NewApi")
public class MainTab01 extends Fragment {
    private ViewPager mPager;
    private List<View> listViews;
    private ImageView cursor;
    private TextView titlename ,context11,context12,context21,context22,context31,context32;
    private LinearLayout vpcontainer01, vpcontainer02, vpcontainer03;
    private int offset = 0;
    private int currIndex = 0;
    private int bmpW;
    private FilterPopupWindow popupWindow;
    private View filtermain;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_tabbar_mainview1, container, false);

        titlename = (TextView) view.findViewById(R.id.titlename);
        titlename.setText("任务信息");

        filtermain = view.findViewById(R.id.filtermain);

        // 筛选按钮
        Button filterLayout = (Button) view.findViewById(R.id.shaixuan_button);
//        shaixuanbut.setVisibility(view.GONE); //隐藏
        // 筛选点击监听
        filterLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                popupWindow = new FilterPopupWindow(getActivity());
                popupWindow.showFilterPopup(filtermain);
            }
        });

        vpcontainer01 = (LinearLayout) view.findViewById(R.id.vpcontainer01);
        vpcontainer02 = (LinearLayout) view.findViewById(R.id.vpcontainer02);
        vpcontainer03 = (LinearLayout) view.findViewById(R.id.vpcontainer03);

        context11 = (TextView) view.findViewById(R.id.container01_text1);
        context12 = (TextView) view.findViewById(R.id.container01_text2);  //未配载订单数量
        context21 = (TextView) view.findViewById(R.id.container02_text1);
        context22 = (TextView) view.findViewById(R.id.container02_text2);  //已配载订单数量
        context31 = (TextView) view.findViewById(R.id.container03_text1);
        context32 = (TextView) view.findViewById(R.id.container03_text2);  //已完成订单数量

        vpcontainer01.setOnClickListener(new MyOnClickListener(0));
        vpcontainer02.setOnClickListener(new MyOnClickListener(1));
        vpcontainer03.setOnClickListener(new MyOnClickListener(2));

        cursor = (ImageView) view.findViewById(R.id.cursor);
        bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.redline2).getWidth();
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;
        offset = (screenW / 3 - bmpW) / 2;
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        cursor.setImageMatrix(matrix);

        mPager = (ViewPager) view.findViewById(R.id.vPager);
        listViews = new ArrayList<View>();
        listViews.add(inflater.inflate(R.layout.activity_task_viewpager01, null));  //未配载订单页面
        listViews.add(inflater.inflate(R.layout.activity_task_viewpager02, null));  //已配载订单页面
        listViews.add(inflater.inflate(R.layout.activity_task_viewpager03, null));  //已完成订单页面
        mPager.setAdapter(new MyPagerAdapter(listViews));
        mPager.setCurrentItem(0);
        mPager.setOnPageChangeListener(new MyOnPageChangeListener());

        return view;
    }

    public class MyOnClickListener implements View.OnClickListener {
        private int index = 0;

        public MyOnClickListener(int i) {
            index = i;
        }

        @Override
        public void onClick(View v) {
            mPager.setCurrentItem(index);
        }
    }

    ;

    //    private void InitViewPager() {
//        
//    }
    public class MyPagerAdapter extends PagerAdapter {
        public List<View> mListViews;

        public MyPagerAdapter(List<View> mListViews) {
            this.mListViews = mListViews;
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView(mListViews.get(arg1));
        }

        @Override
        public void finishUpdate(View arg0) {
        }

        @Override
        public int getCount() {
            return mListViews.size();
        }

        @Override
        public Object instantiateItem(View arg0, int arg1) {
            ((ViewPager) arg0).addView(mListViews.get(arg1), 0);
            return mListViews.get(arg1);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == (arg1);
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {
        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void startUpdate(View arg0) {
        }
    }
//    private void InitImageView() {
//       
//    }

    public class MyOnPageChangeListener implements OnPageChangeListener {

        int one = offset * 2 + bmpW;
        int two = one * 2;

        @Override
        public void onPageSelected(int arg0) {
            Animation animation = null;
            switch (arg0) {
                case 0:
                    if (currIndex == 1) {
                        animation = new TranslateAnimation(one, 0, 0, 0);
                    } else if (currIndex == 2) {
                        animation = new TranslateAnimation(two, 0, 0, 0);
                    }
                    context11.setTextColor(Color.parseColor("#EA402F"));
                    context12.setTextColor(Color.parseColor("#EA402F"));
                    context21.setTextColor(Color.parseColor("#666666"));
                    context22.setTextColor(Color.parseColor("#666666"));
                    context31.setTextColor(Color.parseColor("#666666"));
                    context32.setTextColor(Color.parseColor("#666666"));
                    break;
                case 1:
                    if (currIndex == 0) {
                        animation = new TranslateAnimation(offset, one, 0, 0);
                    } else if (currIndex == 2) {
                        animation = new TranslateAnimation(two, one, 0, 0);
                    }
                    context11.setTextColor(Color.parseColor("#666666"));
                    context12.setTextColor(Color.parseColor("#666666"));
                    context21.setTextColor(Color.parseColor("#EA402F"));
                    context22.setTextColor(Color.parseColor("#EA402F"));
                    context31.setTextColor(Color.parseColor("#666666"));
                    context32.setTextColor(Color.parseColor("#666666"));
                    break;
                case 2:
                    if (currIndex == 0) {
                        animation = new TranslateAnimation(offset, two, 0, 0);
                    } else if (currIndex == 1) {
                        animation = new TranslateAnimation(one, two, 0, 0);
                    }
                    context11.setTextColor(Color.parseColor("#666666"));
                    context12.setTextColor(Color.parseColor("#666666"));
                    context21.setTextColor(Color.parseColor("#666666"));
                    context22.setTextColor(Color.parseColor("#666666"));
                    context31.setTextColor(Color.parseColor("#EA402F"));
                    context32.setTextColor(Color.parseColor("#EA402F"));
                    break;
            }
            currIndex = arg0;
            animation.setFillAfter(true);
            animation.setDuration(300);
            cursor.startAnimation(animation);
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    }
}
