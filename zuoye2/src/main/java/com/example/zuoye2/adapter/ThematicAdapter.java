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
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.zuoye2.R;
import com.example.zuoye2.bean.WanBean;

import java.util.ArrayList;

/**
 * Created by 康康 on 2019/5/27.
 */

public class ThematicAdapter extends RecyclerView.Adapter {
    private ArrayList<WanBean.DataBean.DatasBean> mList;
    private Context mContext;

    public void setList(ArrayList<WanBean.DataBean.DatasBean> list) {
        mList = list;
    }

    public ThematicAdapter(ArrayList<WanBean.DataBean.DatasBean> list, Context context) {
        mList = list;
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.fragment_thematic_item1, null, false);
        return new Viewholder1(inflate);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            Viewholder1 viewholder1= (Viewholder1) holder;
            Glide.with(mContext).load(mList.get(position).getEnvelopePic()).apply(new RequestOptions().circleCrop()).into(viewholder1.iv);

            viewholder1.tv.setText(mList.get(position).getChapterName());

    }



    @Override
    public int getItemCount() {
        return mList.size();
    }
    class Viewholder1 extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tv;
        public Viewholder1(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            tv = itemView.findViewById(R.id.tv);
        }
    }


}
