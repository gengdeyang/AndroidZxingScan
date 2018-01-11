package com.yoreach.carriersapp.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class UndoneTaskBean implements Parcelable {

	private String arrivedTime;
	private String yundanNO;
	private String shopName;
	private String carNum;
	private String suvNum;

	public UndoneTaskBean(String arrivedTime, String yundanNO, String shopName, String carNum, String suvNum ) {
		this.arrivedTime = arrivedTime;
		this.yundanNO = yundanNO;
		this.shopName = shopName;
		this.carNum = carNum;
		this.suvNum = suvNum;
	}
	public UndoneTaskBean() {

	}

	public String getYundanNO() {
		return yundanNO;
	}

	public void setYundanNO(String yundanNO) {
		this.yundanNO = yundanNO;
	}

	public String getArrivedTime() {
		return arrivedTime;
	}

	public void setArrivedTime(String arrivedTime) {
		this.arrivedTime = arrivedTime;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getCarNum() {
		return carNum;
	}

	public void setCarNum(String CarNum) {
		this.carNum = CarNum;
	}
	public String getSuvNum() {
		return suvNum;
	}

	public void setSuvNum(String suvNum) {
		this.suvNum = suvNum;
	}


	//静态的Parcelable.Creator接口
	public static final Creator<UndoneTaskBean> CREATOR = new Creator<UndoneTaskBean>() {

		//创建出类的实例，并从Parcel中获取数据进行实例化
		public UndoneTaskBean createFromParcel(Parcel source) {
			UndoneTaskBean parInfo = new UndoneTaskBean();
			parInfo.arrivedTime = source.readString();
			parInfo.yundanNO= source.readString();
			parInfo.shopName = source.readString();
			parInfo.carNum = source.readString();
			parInfo.suvNum = source.readString();

			return parInfo;
		}

		public UndoneTaskBean[] newArray(int size) {
			// TODO Auto-generated method stub
			return new UndoneTaskBean[size];
		}

	};
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	//将数据写入外部提供的Parcel中
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(arrivedTime);
		dest.writeString(yundanNO);
		dest.writeString(shopName);
		dest.writeString(carNum);
		dest.writeString(suvNum);
	}
}
