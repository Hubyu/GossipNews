package com.moon.gossipnews.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.moon.gossipnews.R;
import com.moon.gossipnews.adapter.HotAdapter;
import com.moon.gossipnews.bean.HotBean;
import com.moon.gossipnews.contant.UrlStrings;
import com.moon.gossipnews.utils.VolleyUtils;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class HotFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private PtrClassicFrameLayout ptrFl;
    private List<HotBean.DataBean> mDatas = new ArrayList<>();
    private HotAdapter mAdapter;
    public HotFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_hot, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rlv);
        ptrFl = (PtrClassicFrameLayout) view.findViewById(R.id.ptrFl);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAdapter = new HotAdapter(getActivity(),mDatas);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        refresh();
        LoaderData();
    }

    private void LoaderData() {
        VolleyUtils.newInstance(getActivity()).newGsonRequest(Request.Method.GET, UrlStrings.URL_HOT, new Response.Listener<HotBean>() {
            @Override
            public void onResponse(HotBean response) {
                if(response != null){
                    mDatas.addAll(response.body.datas);
                    mAdapter.notifyDataSetChanged();
                }
            }
        },null,HotBean.class);
    }
    private void refresh(){
        ptrFl.setResistance(1.5f);
        //是否是下拉刷新
        ptrFl.setPullToRefresh(false);
        //头部高度的几倍再去刷新
        ptrFl.setRatioOfHeaderHeightToRefresh(1.2f);
        //刷新的时候，是否保持头部
        ptrFl.setKeepHeaderWhenRefresh(true);
        //恢复到头部的位置的时长
        ptrFl.setDurationToClose(200);
        //头部整体关闭的时常
        ptrFl.setDurationToCloseHeader(1000);
        ptrFl.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                ptrFl.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.notifyDataSetChanged();
                        ptrFl.refreshComplete();
                    }
                },3000);
            }
        });
    }
}
