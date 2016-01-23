package com.hxp.happyschool.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hxp.happyschool.R;

import java.util.List;

/**
 * Created by hxp on 16-1-23.
 */
public class WifiAdapter extends Adapter<myViewHolder> {


    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private List<String> mDatas;


    public WifiAdapter(Context context, List<String> datas) {
        this.mContext = context;
        this.mDatas = datas;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = mLayoutInflater.inflate(R.layout.wifitem_leader, parent, false);
        myViewHolder mmyViewHolder = new myViewHolder(mView);
        return mmyViewHolder;
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {
        holder.tv.setText(position);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}

class myViewHolder extends ViewHolder {

    TextView tv;

    public myViewHolder(View itemView) {
        super(itemView);
        tv = (TextView) itemView.findViewById(R.id.tv);
    }
}
