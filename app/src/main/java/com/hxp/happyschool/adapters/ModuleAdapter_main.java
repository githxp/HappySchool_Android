package com.hxp.happyschool.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.hxp.happyschool.R;

import java.util.List;


/**
 * Created by hxp on 16-1-20.
 */
public class ModuleAdapter_main extends RecyclerView.Adapter<MyViewHolder> {


    //设置成员变量
    private Context mContext;
    private List<String> mDatas;
    private LayoutInflater mLayoutInflater;


    //创建构造方法
    public ModuleAdapter_main(Context context, List<String> datas) {
        this.mContext = context;
        this.mDatas = datas;
        mLayoutInflater = LayoutInflater.from(context);


    }


    //设置模块栏按钮OnClickListener回调接口
    public interface OnItemClickListener {
        public void OnItemClick(View view, int positon);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = mLayoutInflater.inflate(R.layout.rvmoduleitem_main, parent, false);
        MyViewHolder mViewHolder = new MyViewHolder(mView);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.btnModule_main.setText(mDatas.get(position));


        //为模块栏按钮绑定事件侦听
        if (mOnItemClickListener != null) {
            holder.btnModule_main.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.OnItemClick(holder.btnModule_main, position);
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}

class MyViewHolder extends ViewHolder {

    Button btnModule_main;

    public MyViewHolder(View itemView) {
        super(itemView);
        btnModule_main = (Button) itemView.findViewById(R.id.btnModule_main);
    }
}
