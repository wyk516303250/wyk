package com.example.zuoye2.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zuoye2.R;
import com.example.zuoye2.bean.WanBean;

import java.util.ArrayList;

/**
 * Created by 康康 on 2019/5/28.
 */

public class BabyAdapter extends RecyclerView.Adapter {
    private ArrayList<WanBean.DataBean.DatasBean> mList;
    private Context mContext;

    public void setList(ArrayList<WanBean.DataBean.DatasBean> list) {
        mList = list;
    }

    public BabyAdapter(ArrayList<WanBean.DataBean.DatasBean> list, Context context) {
        mList = list;
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.fragment_baby_item, parent, false);
        return new Viewholder5(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Viewholder5 viewholder5= (Viewholder5) holder;
        Glide.with(mContext).load(mList.get(position).getEnvelopePic()).into(viewholder5.iv);
        viewholder5.tv.setText(mList.get(position).getChapterName());
        viewholder5.tv1.setText(mList.get(position).getSuperChapterName());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    class Viewholder5 extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tv;
        TextView tv1;
        public Viewholder5(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv3);
            tv = itemView.findViewById(R.id.tv5);
            tv1 = itemView.findViewById(R.id.tv6);
        }
    }
}
