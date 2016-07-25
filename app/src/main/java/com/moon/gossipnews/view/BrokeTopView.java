package com.moon.gossipnews.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.moon.gossipnews.R;

/**
 * Created by Administrator on 2016/7/20.
 */
public class BrokeTopView extends LinearLayout {
    public BrokeTopView(Context context) {
        this(context,null);
    }

    public BrokeTopView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BrokeTopView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_broke_top,this);
    }
}
