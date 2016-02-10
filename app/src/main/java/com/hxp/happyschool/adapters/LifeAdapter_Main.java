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
    private OnItemClickListener mOnItemClickListener;


    //创建单击回调接口
    public interface OnItemClickListener {
        void OnItemClick(View view, int position);
    }


    //创建回调单击方法
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }


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
    public void onBindViewHolder(final LifeViewHolder_Main holder, final int position) {
        holder.tv_title_lifeitem.setText(datas.get(position).getmItemName());
        holder.img_icon_lifeitem.setImageResource(datas.get(position).getmImageResource());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.OnItemClick(holder.itemView, position);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return datas.size();
    }
}


class LifeViewHolder_Main extends ViewHolder {

    TextView tv_title_lifeitem;
    ImageView img_icon_lifeitem;

    public LifeViewHolder_Main(View itemView) {
        super(itemView);
        tv_title_lifeitem = (TextView) itemView.findViewById(R.id.tv_title_lifeitem);
        img_icon_lifeitem = (ImageView) itemView.findViewById(R.id.img_icon_lifeitem);
    }


}

