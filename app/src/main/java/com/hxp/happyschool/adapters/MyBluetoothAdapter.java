
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

public class MyBluetoothAdapter extends Adapter<mBluetoothViewHolder> {


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
    public mBluetoothViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = mLayoutInflater.inflate(R.layout.bluetoothitem_location, parent, false);
        mBluetoothViewHolder mmyViewHolder = new mBluetoothViewHolder(mView);
        return mmyViewHolder;
    }

    @Override
    public void onBindViewHolder(mBluetoothViewHolder holder, int position) {
        //mWifiBean = mBeans.get(position);
        holder.tvBluetoothName_location.setText("蓝牙1");
        holder.tvBluetoothDistance_location.setText("12米");
    }

    @Override
    public int getItemCount() {
        return 3;
    }

}

class mBluetoothViewHolder extends RecyclerView.ViewHolder {

    TextView tvBluetoothName_location;
    TextView tvBluetoothDistance_location;

    public mBluetoothViewHolder(View itemView) {
        super(itemView);
        tvBluetoothName_location = (TextView) itemView.findViewById(R.id.tvBluetoothName_location);
        tvBluetoothDistance_location = (TextView) itemView.findViewById(R.id.tvBluetoothDistance_location);
    }
}


