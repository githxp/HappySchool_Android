
package com.hxp.happyschool.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
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

public class MyBluetoothAdapter extends Adapter<BluetoothViewHolder> {


    private LayoutInflater mLayoutInflater;
    private Context mContext;
    //private List<WifiBean> mBeans;
    //private WifiBean mWifiBean;


    public MyBluetoothAdapter(Context context) {
        this.mContext = context;
        //this.mBeans = beans;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public BluetoothViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = mLayoutInflater.inflate(R.layout.bluetoothitem_location, parent, false);
        BluetoothViewHolder mmyViewHolder = new BluetoothViewHolder(mView);
        return mmyViewHolder;
    }

    @Override
    public void onBindViewHolder(BluetoothViewHolder holder, int position) {
        //mWifiBean = mBeans.get(position);
        holder.tv_name_bluetoth_location.setText("蓝牙1");
        holder.tv_distance_bluetooth_location.setText("12米");
    }

    @Override
    public int getItemCount() {
        return 3;
    }

}

class BluetoothViewHolder extends RecyclerView.ViewHolder {

    TextView tv_name_bluetoth_location;
    TextView tv_distance_bluetooth_location;

    public BluetoothViewHolder(View itemView) {
        super(itemView);
        tv_name_bluetoth_location = (TextView) itemView.findViewById(R.id.tv_name_bluetoth_location);
        tv_distance_bluetooth_location = (TextView) itemView.findViewById(R.id.tv_distance_bluetooth_location);
    }
}


