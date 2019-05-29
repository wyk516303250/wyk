package com.example.zuoye2.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.zuoye2.R;
import com.example.zuoye2.bean.WanBean;

import java.util.ArrayList;

/**
 * Created by 康康 on 2019/5/28.
 */

public class AllAdapter extends RecyclerView.Adapter {
    private ArrayList<WanBean.DataBean.DatasBean> mList;
    private Context mContext;

    public void setList(ArrayList<WanBean.DataBean.DatasBean> list) {
        mList = list;
    }

    public AllAdapter(ArrayList<WanBean.DataBean.DatasBean> list, Context context) {
        mList = list;
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.fragment_all_item, parent, false);

        return new Viewholder4(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Viewholder4 viewholder4= (Viewholder4) holder;
        Glide.with(mContext).load(mList.get(position).getEnvelopePic()).apply(new RequestOptions().transform(new RoundedCorners(100))).into(viewholder4.iv2);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class Viewholder4 extends RecyclerView.ViewHolder {
        ImageView iv2;
        public Viewholder4(View itemView) {
            super(itemView);
            iv2 = itemView.findViewById(R.id.iv2);
        }
    }
}
