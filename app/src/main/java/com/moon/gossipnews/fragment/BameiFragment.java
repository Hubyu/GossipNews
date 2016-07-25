package com.moon.gossipnews.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.moon.gossipnews.R;
import com.moon.gossipnews.adapter.LvBameiAdapter;
import com.moon.gossipnews.bean.BameiBean;
import com.moon.gossipnews.constant.UrlString;
import com.moon.gossipnews.utils.VolleyUtils;
import com.moon.gossipnews.view.BameiTopView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class BameiFragment extends Fragment {
    private ListView lv_bamei;
    private ArrayList<BameiBean.DatasBean> data = new ArrayList<>();
    private LvBameiAdapter adapter;
    private Activity mActivity;
    private BameiTopView bameiTopView;
    public BameiFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity=getActivity();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bamei, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        lv_bamei= (ListView) view.findViewById(R.id.lv_bamei);
        adapter=new LvBameiAdapter(mActivity,data);
        bameiTopView=new BameiTopView(mActivity);
        lv_bamei.addHeaderView(bameiTopView);
        lv_bamei.setAdapter(adapter);
        loadData();
    }

    private void loadData() {
        VolleyUtils.newInstance(getActivity()).newGsonRequest(Request.Method.GET, UrlString.URL_BAMEI,
                new Response.Listener<BameiBean>() {
                    @Override
                    public void onResponse(BameiBean response) {
                        data.addAll(response.body.datas);
                        adapter.notifyDataSetChanged();
                    }
                },null,BameiBean.class);
    }
}
