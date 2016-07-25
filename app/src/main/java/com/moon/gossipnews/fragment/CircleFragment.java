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
import com.moon.gossipnews.adapter.VpAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 */
public class CircleFragment extends Fragment {
    @InjectView(R.id.tl)
    TabLayout tl;
    @InjectView(R.id.vp_content)
    ViewPager vpContent;
    private List<Fragment> mFragment;
    private List<String> mTabs;

    public CircleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_circle, container, false);
        ButterKnife.inject(this, view);
        return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragment=new ArrayList<>();
        mTabs=new ArrayList<>();
        initData();
    }
    private void initData() {
        mFragment.add(new BrokeFragment());
        mFragment.add(new BameiFragment());
        mFragment.add(new ActivityFragment());
        vpContent.setAdapter(new VpAdapter(getChildFragmentManager(),mFragment));
        tl.setupWithViewPager(vpContent);
        mTabs.add("八卦爆料");
        mTabs.add("问八妹");
        mTabs.add("活动");
        for (int i = 0; i < tl.getTabCount(); i++) {
            TabLayout.Tab tab = tl.getTabAt(i);
            tab.setText(mTabs.get(i));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
