package com.moon.gossipnews.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.moon.gossipnews.R;
import com.moon.gossipnews.activity.ContentActivity;
import com.moon.gossipnews.adapter.HotAdapter;
import com.moon.gossipnews.bean.HotBean;
import com.moon.gossipnews.contant.UrlStrings;
import com.moon.gossipnews.utils.VolleyUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;


/**
 * A simple {@link Fragment} subclass.
 */
public class HotFragment extends Fragment {
    private ListView mListView;
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
        mListView = (ListView) view.findViewById(R.id.lv_hot);
        ptrFl = (PtrClassicFrameLayout) view.findViewById(R.id.ptrFl);
        Log.d("tag", "1: ");
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("tag", "2: ");
                String s = mDatas.get(position).id;
                String format = UrlStrings.URL_CONTENT+s;
//      //          String format = String.format(UrlStrings.URL_CONTENT, s);
                Log.d("tag",format);
                Intent intent = new Intent(getActivity(),ContentActivity.class);
             /*   Bundle bundle = new Bundle();
                bundle.putString("content",);*/
                intent.putExtra("content",format);
                startActivity(intent);
            }
        });
        Log.d("tag", "3: ");
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAdapter = new HotAdapter(getActivity(),mDatas);
        mListView.setAdapter(mAdapter);
        refresh();
        LoaderData();
//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String s = mDatas.get(position).id;
//                String format = String.format(UrlStrings.URL_CONTENT, s);
//                Intent intent = new Intent(getActivity(),ContentActivity.class);
//             /*   Bundle bundle = new Bundle();
//                bundle.putString("content",);*/
//                intent.putExtra("content",format);
//                startActivity(intent);
//                Log.i()
//            }
//        });
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
