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
import com.moon.gossipnews.adapter.LvBrokeAdapter;
import com.moon.gossipnews.bean.BrokeBean;
import com.moon.gossipnews.constant.UrlString;
import com.moon.gossipnews.utils.VolleyUtils;
import com.moon.gossipnews.view.BrokeTopView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class BrokeFragment extends Fragment {
    private ListView lv_broke;
    private ArrayList<BrokeBean.DatasBean> data = new ArrayList<>();
    private LvBrokeAdapter adapter;
    private BrokeTopView mBrokeTopView;
    private Activity mActivity;
    public BrokeFragment() {
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
        return inflater.inflate(R.layout.fragment_broke, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        lv_broke= (ListView) view.findViewById(R.id.lv_broke);
        adapter=new LvBrokeAdapter(getActivity(),data);
        mBrokeTopView=new BrokeTopView(mActivity);
        lv_broke.addHeaderView(mBrokeTopView);
        lv_broke.setAdapter(adapter);
        loadData();
    }

    private void loadData() {
        VolleyUtils.newInstance(getActivity()).newGsonRequest(Request.Method.GET, UrlString.URL_BROKE,
                new Response.Listener<BrokeBean>() {
                    @Override
                    public void onResponse(BrokeBean response) {
                        data.addAll(response.body.datas);
                        adapter.notifyDataSetChanged();
                    }
                },null,BrokeBean.class);
    }
}
