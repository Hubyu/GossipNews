package com.moon.gossipnews.fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;


import com.moon.gossipnews.R;
import com.moon.gossipnews.activity.SecondaryCategoryActivity;
import com.moon.gossipnews.adapter.CategoryAdapter;


import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment {
    private ListView mListView;
    private Activity mAvtivity;
    private CategoryAdapter mAdapter;
    private List<View> mLists;
    private List<String> mUrls;
    private int[] images_category = new int[]{R.mipmap.cate_dashijianpng, R.mipmap.cate_bigboss, R.mipmap.cate_baguamengliao, R.mipmap.cate_zhangzishi,
            R.mipmap.cate_dajixue, R.mipmap.cate_yuwangdushi, R.mipmap.cate_hahaha, R.mipmap.cate_shenhuifu, R.mipmap.cate_renshengbaitai, R.mipmap.cate_wuchuanfang};

    public CategoryFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAvtivity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = LayoutInflater.from(mAvtivity).inflate(R.layout.fragment_category, container, false);
        mListView = (ListView) view.findViewById(R.id.lv_category);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        mAdapter = new CategoryAdapter(mAvtivity, mLists);
        mListView.setAdapter(mAdapter);
        getData();
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(),SecondaryCategoryActivity.class);
                intent.putExtra("url",mUrls.get(position));
                startActivity(intent);
            }
        });

    }

    private void getData() {
        mUrls=new ArrayList<>();
        mUrls.add("http://www.jinrongbaguanv.com/jinba/articles/100000/1");
        mUrls.add("http://www.jinrongbaguanv.com/jinba/articles/100001/1");
        mUrls.add("http://www.jinrongbaguanv.com/jinba/articles/100002/1");
        mUrls.add("http://www.jinrongbaguanv.com/jinba/articles/100003/1");
        mUrls.add("http://www.jinrongbaguanv.com/jinba/articles/100004/1");
        mUrls.add("http://www.jinrongbaguanv.com/jinba/articles/100005/1");
        mUrls.add("http://www.jinrongbaguanv.com/jinba/articles/100006/1");
        mUrls.add("http://www.jinrongbaguanv.com/jinba/articles/100007/1");
        mUrls.add("http://www.jinrongbaguanv.com/jinba/articles/100008/1");
        mUrls.add("http://www.jinrongbaguanv.com/jinba/articles/100009/1");
    }

    private void initData() {
        mLists = new ArrayList<View>();
        for (int i = 0; i < images_category.length; i++) {
            ImageView iv = new ImageView(mAvtivity);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 400);
            iv.setLayoutParams(lp);
            iv.setImageResource(images_category[i]);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            mLists.add(iv);
        }
    }

}
