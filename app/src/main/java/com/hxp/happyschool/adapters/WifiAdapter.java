package com.hxp.happyschool.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hxp.happyschool.R;
import com.hxp.happyschool.beans.WifiBean;

import java.util.List;

/**
 * wifi定位列表适配器
 * Created by hxp on 16-1-23.
 */
public class WifiAdapter extends Adapter<WifiViewHolder_Main> {


    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<WifiBean> mBeans;
    private WifiBean mWifiBean;


    public WifiAdapter(Context context, List<WifiBean> beans) {
        this.mContext = context;
        this.mBeans = beans;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public WifiViewHolder_Main onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = mLayoutInflater.inflate(R.layout.wifitem_location, parent, false);
        WifiViewHolder_Main mmyViewHolder = new WifiViewHolder_Main(mView);
        return mmyViewHolder;
    }

    @Override
    public void onBindViewHolder(WifiViewHolder_Main holder, int position) {
        mWifiBean = mBeans.get(position);
        holder.tv_ssid_wifi_locationitem.setText(mWifiBean.getSsid());
        holder.tv_address_wifi_locationitem.setText(mWifiBean.getAddress());
        holder.tv_distance_wifi_locationitem.setText(mWifiBean.getDistance() + "米");
    }

    @Override
    public int getItemCount() {
        return mBeans.size();
    }
}

class WifiViewHolder_Main extends ViewHolder {

    TextView tv_ssid_wifi_locationitem;
    TextView tv_address_wifi_locationitem;
    TextView tv_distance_wifi_locationitem;

    public WifiViewHolder_Main(View itemView) {
        super(itemView);
        tv_ssid_wifi_locationitem = (TextView) itemView.findViewById(R.id.tv_ssid_wifi_locationitem);
        tv_address_wifi_locationitem = (TextView) itemView.findViewById(R.id.tv_address_wifi_locationitem);
        tv_distance_wifi_locationitem = (TextView) itemView.findViewById(R.id.tv_distance_wifi_locationitem);
    }
}
