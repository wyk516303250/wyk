package com.example.zuoye2;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.zuoye2.adapter.VpAdapter;
import com.example.zuoye2.fragment.BabyFragment;
import com.example.zuoye2.fragment.ThematicFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TabLayout mTl;
    private ViewPager mVp;
    private ArrayList<Fragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        mFragments = new ArrayList<>();
        mFragments.add(new ThematicFragment());
        mFragments.add(new BabyFragment());

        VpAdapter vpAdapter = new VpAdapter(getSupportFragmentManager(), mFragments);
        mVp.setAdapter(vpAdapter);

        mTl.addTab(mTl.newTab().setText("专题活动"));
        mTl.addTab(mTl.newTab().setText("人气宝贝"));

        mTl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
        mVp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTl));
    }

    private void initView() {
        mTl = (TabLayout) findViewById(R.id.tl);
        mVp = (ViewPager) findViewById(R.id.vp);
    }
}
