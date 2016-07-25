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
import com.moon.gossipnews.adapter.RvActivityAdapter;
import com.moon.gossipnews.bean.ActivityBean;
import com.moon.gossipnews.constant.UrlString;
import com.moon.gossipnews.utils.VolleyUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActivityFragment extends Fragment {
    private RecyclerView mRecycleView;
    private List<ActivityBean.DatasBean> mDatas;
    private RvActivityAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    public ActivityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_activity, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecycleView= (RecyclerView) view.findViewById(R.id.rv_activity);
        mDatas=new ArrayList<>();
        mAdapter=new RvActivityAdapter(getActivity(),mDatas);
        mRecycleView.setAdapter(mAdapter);
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRecycleView.setLayoutManager(mLinearLayoutManager);
        loadData();
    }

    private void loadData() {
        VolleyUtils.newInstance(getActivity()).newGsonRequest(Request.Method.GET, UrlString.URL_ACTIVITY, new Response.Listener<ActivityBean>() {

            @Override
            public void onResponse(ActivityBean response) {
                mDatas.addAll(response.body.datas);
                mAdapter.notifyDataSetChanged();
            }
        }, null, ActivityBean.class);
    }
}
