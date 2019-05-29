package com.example.zuoye2.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zuoye2.R;
import com.example.zuoye2.adapter.ThematicAdapter;
import com.example.zuoye2.adapter.ThematicAdapter1;
import com.example.zuoye2.adapter.VpAdapter;
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
 * Created by 康康 on 2019/5/27.
 */

public class ThematicFragment extends Fragment {

    /**
     * zuoye2
     */

    private TabLayout mTll;
    private ViewPager mVp;
    private ArrayList<WanBean.DataBean.DatasBean> mList = new ArrayList<>();
    private ThematicAdapter mAdapter;
    private View view;
    private RecyclerView mRlv;
    private ThematicAdapter1 mAdapter1;
    private RecyclerView mRlv1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_thematic, null, false);
        initView(inflate);
        initData();
        initData1();
        return inflate;
    }

    private void initData1() {
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
                        mAdapter1.setList(mList);
                        mAdapter1.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
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
        mTll = (TabLayout) inflate.findViewById(R.id.tll);
        mVp = (ViewPager) inflate.findViewById(R.id.vp);

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new AllFragment());
        fragments.add(new SelectFragment());

        VpAdapter vpAdapter = new VpAdapter(getChildFragmentManager(), fragments);
        mVp.setAdapter(vpAdapter);

        mTll.addTab(mTll.newTab().setText("全部最新"));
        mTll.addTab(mTll.newTab().setText("最热精选"));

        mTll.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mVp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mVp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTll));

        mRlv = (RecyclerView) inflate.findViewById(R.id.rlv);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRlv.setLayoutManager(manager);
        mAdapter = new ThematicAdapter(mList, getContext());
        mRlv.setAdapter(mAdapter);


        mRlv1 = (RecyclerView) inflate.findViewById(R.id.rlv1);
        LinearLayoutManager manager1 = new LinearLayoutManager(getContext());
        manager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRlv1.setLayoutManager(manager1);
        mAdapter1  = new ThematicAdapter1(mList, getContext());
        mRlv1.setAdapter(mAdapter1);


    }
}
