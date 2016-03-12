package com.hxp.happyschool.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.hxp.happyschool.R;
import com.hxp.happyschool.beans.FileScanBean;
import com.hxp.happyschool.beans.LifeBean;

import java.util.List;

/**
 * Created by hxp on 16-3-12.
 */
public class SelectUploadFileAdapter extends RecyclerView.Adapter<SelectUploadFileViewHolder_ClassUnion> {
    private LayoutInflater mLayoutInflater;
    private List<FileScanBean> datas;
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
    public SelectUploadFileAdapter(Context context, List<FileScanBean> datas) {
        mLayoutInflater = LayoutInflater.from(context);
        this.datas = datas;
    }


    //重写方法
    @Override
    public SelectUploadFileViewHolder_ClassUnion onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.selectuploadfileitem, parent, false);
        SelectUploadFileViewHolder_ClassUnion mSelectUploadFileViewHolder_ClassUnion = new SelectUploadFileViewHolder_ClassUnion(view);
        return mSelectUploadFileViewHolder_ClassUnion;
    }


    @Override
    public void onBindViewHolder(final SelectUploadFileViewHolder_ClassUnion holder, final int position) {
        holder.tv_selectFile_classunion.setText(datas.get(position).getFileName());
        holder.checkbox_selectFile_classunion.setChecked(datas.get(position).isChecked());
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


class SelectUploadFileViewHolder_ClassUnion extends RecyclerView.ViewHolder {

    TextView tv_selectFile_classunion;
    CheckBox checkbox_selectFile_classunion;

    public SelectUploadFileViewHolder_ClassUnion(View itemView) {
        super(itemView);
        tv_selectFile_classunion = (TextView) itemView.findViewById(R.id.tv_selectFile_classunion);
        checkbox_selectFile_classunion = (CheckBox) itemView.findViewById(R.id.checkbox_selectFile_classunion);
    }
}
