package com.example.zuoye2.api;

import com.example.zuoye2.bean.WanBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by 康康 on 2019/5/27.
 */

public interface Myserver  {
    String WanUrl="http://www.wanandroid.com/project/list/1/";
    @GET("json?cid=294")
    Observable<WanBean> getData();
}
