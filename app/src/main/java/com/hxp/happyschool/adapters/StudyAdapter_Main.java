package com.hxp.happyschool.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hxp.happyschool.R;
import com.hxp.happyschool.beans.StudyBean;

import java.util.List;

/**
 * Created by hxp on 16-2-2.
 */
public class StudyAdapter_Main extends Adapter<StudyViewHolder_Main> {


    private LayoutInflater mLayoutInflater;
    private List<StudyBean> datas;
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
    public StudyAdapter_Main(Context context, List<StudyBean> datas) {
        mLayoutInflater = LayoutInflater.from(context);
        this.datas = datas;
    }


    //重写方法
    @Override
    public StudyViewHolder_Main onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.studyitem, parent, false);
        StudyViewHolder_Main mStudyViewHolder_Main = new StudyViewHolder_Main(view);
        return mStudyViewHolder_Main;
    }


    @Override
    public void onBindViewHolder(final StudyViewHolder_Main holder, final int position) {
        holder.tv_title_studytitem.setText(datas.get(position).getmItemName());
        holder.img_icon_studyitem.setImageResource(datas.get(position).getmImageResource());
        holder.itemView.setOnClickListener(new OnClickListener() {
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


class StudyViewHolder_Main extends ViewHolder {

    TextView tv_title_studytitem;
    ImageView img_icon_studyitem;

    public StudyViewHolder_Main(View itemView) {
        super(itemView);
        tv_title_studytitem = (TextView) itemView.findViewById(R.id.tv_title_studyitem);
        img_icon_studyitem = (ImageView) itemView.findViewById(R.id.img_icon_studyitem);
    }


}
