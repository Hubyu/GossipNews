package com.moon.gossipnews.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moon.gossipnews.R;
import com.moon.gossipnews.adapter.TabAdapter;

import java.util.ArrayList;
import java.util.List;


public class MyFragment extends Fragment {
    private ViewPager mViewPager;
    private List<Fragment> mFragment;
    private TabAdapter mAdapter;
    private TabLayout mTab;
    private List<String> tabs;
    public MyFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_my, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewPager= (ViewPager) view.findViewById(R.id.vp_view);

        mTab= (TabLayout) view.findViewById(R.id.tabs);
        mFragment=new ArrayList<>();
        tabs=new ArrayList<>();
        mAdapter=new TabAdapter(getChildFragmentManager(),mFragment);
        mFragment.add(MyChildFragment.newInstance("记得收藏您喜欢的文章"));
        mFragment.add(MyChildFragment.newInstance("八妹等您来爆料"));
        mFragment.add(MyChildFragment.newInstance("来呀大胆向八妹提问吧~"));
        mFragment.add(MyChildFragment.newInstance("有趣活动等你参加"));
        mViewPager.setAdapter(mAdapter);
        mTab.setupWithViewPager(mViewPager);
        tabs.add("八卦");
        tabs.add("爆料");
        tabs.add("问八妹");
        tabs.add("活动");
        for (int i = 0; i < mTab.getTabCount(); i++) {
            TabLayout.Tab tab = mTab.getTabAt(i);
            tab.setText(tabs.get(i));
        }
    }
}




