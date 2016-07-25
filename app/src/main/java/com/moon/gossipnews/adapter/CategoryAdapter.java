package com.moon.gossipnews.adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.moon.gossipnews.R;
import com.moon.gossipnews.bean.CategoryBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by 王祯 on 2016/7/20.
 */
public class CategoryAdapter extends BaseAdapter {
    private Context mContext;
    private List<View> mLists;
    // private List<CategoryBean.DataBean> mDatas;

    public CategoryAdapter(Context context, List<View> datas) {
        this.mContext = context;
        this.mLists = datas;
    }

    @Override
    public int getCount() {
        return mLists == null ? 0 : mLists.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return mLists.get(position);
    }





}
