package com.yoreach.carriersapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.yoreach.carriersapp.R;
import com.yoreach.carriersapp.bean.UndoneTaskBean;

import java.util.ArrayList;

public class MyListAdapter extends BaseAdapter {
	private Context mContext;
	private ListView mListView;

	private ArrayList<UndoneTaskBean> mData;

	public MyListAdapter(ArrayList<UndoneTaskBean> mData, Context mContext, ListView list){
		this.mData = mData;
		this.mContext = mContext;
		mListView = list;
	}
 
	public int getCount() {
		return mData.size();
	}

	@Override
	public boolean areAllItemsEnabled() {
		return false;
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
//		position = position+1;

		ViewHolder viewHolder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.activity_undotask_listitem, parent, false);
			viewHolder = new ViewHolder();
			viewHolder.textView01 = (TextView) convertView.findViewById(R.id.item_text01);
			viewHolder.textView02 = (TextView) convertView.findViewById(R.id.item_text02);
			viewHolder.textView03 = (TextView) convertView.findViewById(R.id.item_text03);
			viewHolder.textView04 = (TextView) convertView.findViewById(R.id.item_text04);
			viewHolder.textView05 = (TextView) convertView.findViewById(R.id.item_text05);
			convertView.setTag(viewHolder);

		}else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.textView01.setText(mData.get(position).getArrivedTime());
		viewHolder.textView02.setText(mData.get(position).getYundanNO());
		viewHolder.textView03.setText(mData.get(position).getShopName());
		viewHolder.textView04.setText(mData.get(position).getCarNum());
		viewHolder.textView05.setText(mData.get(position).getSuvNum());

		updateBackground(position+1,convertView);

		return convertView;
	}

	private class ViewHolder{
		TextView textView01;
		TextView textView02;
		TextView textView03;
		TextView textView04;
		TextView textView05;
	}
	@SuppressLint("NewApi")
	public void updateBackground(int position, View view) {
		int backgroundId;

		if (mListView.isItemChecked(position)) {
			Log.i("position=",""+position);
			backgroundId = R.mipmap.list_selected_holo_light;  //设置选中时的背景色
		} else {
			backgroundId = R.drawable.conversation_item_background_read;
		}
		Drawable background = mContext.getResources().getDrawable(backgroundId);
		view.setBackground(background);
	}
	public void add(UndoneTaskBean data){
		if (mData == null) {
			mData = new ArrayList<UndoneTaskBean>();
		}
		mData.add(data);
		//删除的话用remove
		notifyDataSetChanged();
	}
}
