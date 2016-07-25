package com.moon.gossipnews.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.moon.gossipnews.R;

/**
 * Created by Administrator on 2016/7/20.
 */
public class BameiTopView extends LinearLayout {

    public BameiTopView(Context context) {
        this(context,null);
    }

    public BameiTopView(Context context, AttributeSet attrs) {
        this(context, attrs,0);

    }

    public BameiTopView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_bamei_top,this);
    }
}
