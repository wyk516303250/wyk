package com.example.zuoye2.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zuoye2.R;
import com.example.zuoye2.adapter.AllAdapter;
import com.example.zuoye2.api.Myserver;
import com.example.zuoye2.bean.WanBean;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 康康 on 2019/5/28.
 */

public class AllFragment extends Fragment {
    private View view;
    private RecyclerView mRlv2;
    private ArrayList<WanBean.DataBean.DatasBean> mList=new ArrayList<>();
    private AllAdapter mAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_all, null, false);
        initView(inflate);
        initData();
        return inflate;
    }

    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Myserver.WanUrl)
                .build();

        Myserver myserver = retrofit.create(Myserver.class);

        Observable<WanBean> data = myserver.getData();

        data.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WanBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(WanBean wanBean) {
                        mList.addAll(wanBean.getData().getDatas());
                        mAdapter.setList(mList);
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void initView(View inflate) {
        mRlv2 = (RecyclerView) inflate.findViewById(R.id.rlv2);
        GridLayoutManager manager = new GridLayoutManager(getContext(), 2);
        mRlv2.setLayoutManager(manager);
        mAdapter = new AllAdapter(mList, getContext());
        mRlv2.setAdapter(mAdapter);
    }
}
