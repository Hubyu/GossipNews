package com.moon.gossipnews.fragment;


import android.app.Activity;
import android.content.Context;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class HotFragment extends Fragment {
    private RecyclerView mRecyclerView;
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
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAdapter = new HotAdapter(getActivity(),mDatas);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
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
}
