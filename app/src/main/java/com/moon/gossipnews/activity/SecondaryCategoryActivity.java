package com.moon.gossipnews.activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.Gson;
import com.moon.gossipnews.R;
import com.moon.gossipnews.adapter.SecCateAdapter;
import com.moon.gossipnews.bean.SecCategoryBean;
import com.moon.gossipnews.bean.SecondaryBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SecondaryCategoryActivity extends AppCompatActivity {
    private String url;
    private List<SecCategoryBean.ContentBean.DetailBean> mDatas;
    private ListView lv_sec;
    private SecCateAdapter mSecCateAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary_category);
        mDatas=new ArrayList<>();
        url = getIntent().getExtras().getString("url");
        lv_sec= (ListView) findViewById(R.id.lv_category_sec);
        mSecCateAdapter=new SecCateAdapter(this,mDatas);
        lv_sec.setAdapter(mSecCateAdapter);
        loadDatas();

    }

    private void loadDatas() {
        OkHttpClient mOkHttpClient=new OkHttpClient();
        Request mRequest=new Request.Builder().url(url).build();
        Call mCall=mOkHttpClient.newCall(mRequest);
        mCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                    final String result=response.body().string();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                       SecCategoryBean bean= new Gson().fromJson(result, SecCategoryBean.class);
                        mDatas.addAll(bean.body.datas);
                        mSecCateAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }
    Handler mHandler=new Handler();
}
