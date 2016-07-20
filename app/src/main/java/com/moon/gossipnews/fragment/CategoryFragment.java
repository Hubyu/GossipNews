package com.moon.gossipnews.fragment;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.moon.gossipnews.R;
import com.moon.gossipnews.adapter.CategoryAdapter;
import com.moon.gossipnews.bean.CategoryBean;
import com.moon.gossipnews.constant.UrlString;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment {
    private RecyclerView mRecyclerView;

    private Activity mAvtivity;
    private CategoryAdapter mAdapter;
    private List<CategoryBean.DataBean> mDatas;
    private LinearLayoutManager mLayoutManager;
    public CategoryFragment(){

    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAvtivity=getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=LayoutInflater.from(mAvtivity).inflate(R.layout.fragment_category,container,false);
        mRecyclerView= (RecyclerView) view.findViewById(R.id.rv_category);

        return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mDatas=new ArrayList<>();
        mAdapter=new CategoryAdapter(mAvtivity,mDatas);
        mRecyclerView.setAdapter(mAdapter);
        mLayoutManager=new LinearLayoutManager(mAvtivity);
        mRecyclerView.setLayoutManager(mLayoutManager);
        loadData();
    }

    private void loadData() {
        OkHttpClient mOkHttpClient=new OkHttpClient();
        Request mRequest=new Request.Builder().url(UrlString.URL_CATEGORY)
                .build();
        Call mCall=mOkHttpClient.newCall(mRequest);

        mCall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response!=null){
                    final String result=response.body().string();
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            CategoryBean bean=new Gson().fromJson(result,CategoryBean.class);
                            mDatas.addAll(bean.body.datas);
                            mAdapter.notifyDataSetChanged();
                        }
                    });
                }

            }
        });
    }
    Handler mHandler=new Handler();
}
