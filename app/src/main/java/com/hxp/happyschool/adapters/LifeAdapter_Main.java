package com.hxp.happyschool.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hxp.happyschool.R;
import com.hxp.happyschool.beans.LifeBean;
import com.hxp.happyschool.beans.StudyBean;

import java.util.List;

/**
 * Created by hxp on 16-2-3.
 */
public class LifeAdapter_Main extends Adapter<LifeViewHolder_Main> {


    private LayoutInflater mLayoutInflater;
    private List<LifeBean> datas;


    //创建构造函数
    public LifeAdapter_Main(Context context, List<LifeBean> datas) {
        mLayoutInflater = LayoutInflater.from(context);
        this.datas = datas;
    }


    //重写方法
    @Override
    public LifeViewHolder_Main onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.lifeitem, parent, false);
        LifeViewHolder_Main mLifeViewHolder_Main = new LifeViewHolder_Main(view);
        return mLifeViewHolder_Main;
    }


    @Override
    public void onBindViewHolder(LifeViewHolder_Main holder, int position) {
        holder.tvtitle_lifeitem.setText(datas.get(position).getmItemName());
        holder.imgicon_lifeitem.setImageResource(datas.get(position).getmImageResource());
    }


    @Override
    public int getItemCount() {
        return datas.size();
    }
}


class LifeViewHolder_Main extends ViewHolder {

    TextView tvtitle_lifeitem;
    ImageView imgicon_lifeitem;

    public LifeViewHolder_Main(View itemView) {
        super(itemView);
        tvtitle_lifeitem = (TextView) itemView.findViewById(R.id.tvtitle_lifeitem);
        imgicon_lifeitem = (ImageView) itemView.findViewById(R.id.imgicon_lifeitem);
    }


}

