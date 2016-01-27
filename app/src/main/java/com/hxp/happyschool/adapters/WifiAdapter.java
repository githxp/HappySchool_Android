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
 * Created by hxp on 16-1-23.
 */
public class WifiAdapter extends Adapter<myViewHolder> {


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
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = mLayoutInflater.inflate(R.layout.wifitem_location, parent, false);
        myViewHolder mmyViewHolder = new myViewHolder(mView);
        return mmyViewHolder;
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {
        mWifiBean = mBeans.get(position);
        holder.tvSsid_location.setText(mWifiBean.getSsid());
        holder.tvAddress_location.setText(mWifiBean.getAddress());
        holder.tvDistance_location.setText(mWifiBean.getDistance());
    }

    @Override
    public int getItemCount() {
        return mBeans.size();
    }
}

class myViewHolder extends ViewHolder {

    TextView tvSsid_location;
    TextView tvAddress_location;
    TextView tvDistance_location;

    public myViewHolder(View itemView) {
        super(itemView);
        tvSsid_location = (TextView) itemView.findViewById(R.id.tvSsid_location);
        tvAddress_location =(TextView) itemView.findViewById(R.id.tvAddress_location);
        tvDistance_location = (TextView) itemView.findViewById(R.id.tvDistance_location);
    }
}
