package com.hxp.happyschool.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hxp.happyschool.R;
import com.hxp.happyschool.beans.EntertainmentBean;
import com.hxp.happyschool.beans.LifeBean;

import java.util.List;

/**
 * Created by hxp on 16-2-3.
 */
public class EntertainmentAdapter extends Adapter<EntertainmentViewHolder_Main> {


    private LayoutInflater mLayoutInflater;
    private List<EntertainmentBean> datas;
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
    public EntertainmentAdapter(Context context, List<EntertainmentBean> datas) {
        mLayoutInflater = LayoutInflater.from(context);
        this.datas = datas;
    }


    //重写方法
    @Override
    public EntertainmentViewHolder_Main onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.entertainmentitem, parent, false);
        EntertainmentViewHolder_Main mEntertainmentViewHolder_Main = new EntertainmentViewHolder_Main(view);
        return mEntertainmentViewHolder_Main;
    }


    @Override
    public void onBindViewHolder(final EntertainmentViewHolder_Main holder, final int position) {
        holder.tv_title_entertainmentitem.setText(datas.get(position).getmItemName());
        holder.img_icon_entertainmentitem.setImageResource(datas.get(position).getmImageResource());
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


class EntertainmentViewHolder_Main extends RecyclerView.ViewHolder {

    TextView tv_title_entertainmentitem;
    ImageView img_icon_entertainmentitem;

    public EntertainmentViewHolder_Main(View itemView) {
        super(itemView);
        tv_title_entertainmentitem = (TextView) itemView.findViewById(R.id.tv_title_entertainmentitem);
        img_icon_entertainmentitem = (ImageView) itemView.findViewById(R.id.img_icon_entertainmentitem);
    }
}
