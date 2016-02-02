package com.hxp.happyschool.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
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
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
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
        View view = mLayoutInflater.inflate(R.layout.mainitem, parent, false);
        StudyViewHolder_Main mStudyViewHolder_Main = new StudyViewHolder_Main(view);
        return mStudyViewHolder_Main;
    }


    @Override
    public void onBindViewHolder(StudyViewHolder_Main holder, int position) {
        holder.tvStduyAdapter_Main.setText(datas.get(position).getmItemName());
        holder.imgClassunion_studyrv.setImageResource(datas.get(position).getmImageResource());
    }


    @Override
    public int getItemCount() {
        return datas.size();
    }
}


class StudyViewHolder_Main extends ViewHolder {

    TextView tvStduyAdapter_Main;
    ImageView imgClassunion_studyrv;

    public StudyViewHolder_Main(View itemView) {
        super(itemView);
        tvStduyAdapter_Main = (TextView) itemView.findViewById(R.id.tvStduyAdapter_Main);
        imgClassunion_studyrv = (ImageView) itemView.findViewById(R.id.imgClassunion_studyrv);
    }


}
