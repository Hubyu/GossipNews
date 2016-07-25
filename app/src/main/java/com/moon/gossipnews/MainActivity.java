package com.moon.gossipnews;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.moon.gossipnews.fragment.CategoryFragment;
import com.moon.gossipnews.fragment.CircleFragment;
import com.moon.gossipnews.fragment.HotFragment;
import com.moon.gossipnews.fragment.MyFragment;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private RadioGroup mRadioGroup;
    private List<Fragment> mFragment;
    private int preIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRadioGroup= (RadioGroup) findViewById(R.id.ra_tabs);
        initFragment();
        showFragment(0,preIndex);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb= (RadioButton) group.findViewById(checkedId);
                int index = group.indexOfChild(rb);
                showFragment(index,preIndex);
                preIndex=index;
            }
        });
    }

    private void initFragment() {
        mFragment=new ArrayList<Fragment>();
        mFragment.add(new HotFragment());
        mFragment.add(new CategoryFragment());
        mFragment.add(new CircleFragment());
        mFragment.add(new MyFragment());
    }
    private void showFragment(int showIndex,int hideIndex) {
        Fragment showFragment = mFragment.get(showIndex);
        Fragment hideFragment = mFragment.get(hideIndex);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (!showFragment.isAdded()) {
            ft.add(R.id.container, showFragment);
        }
        if (showIndex == hideIndex) {
            ft.show(showFragment).commit();
        } else {
            ft.show(showFragment);
            ft.hide(hideFragment);
            ft.commit();
        }
    }
}
