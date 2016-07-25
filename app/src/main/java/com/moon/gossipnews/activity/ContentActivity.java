package com.moon.gossipnews.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.google.gson.Gson;
import com.moon.gossipnews.R;
import com.moon.gossipnews.bean.ContentBean;
import com.moon.gossipnews.utils.VolleyUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/22.
 */
public class ContentActivity extends Activity {
    private List<ContentBean.BodyBean> mData;
    private String Url;
    private ImageView mImageView;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_hot_content);
        mData = new ArrayList<>();
        Url = getIntent().getStringExtra("content");
        initData();

    }

    private void initData() {
        mImageView = (ImageView) findViewById(R.id.iv_cardview);
        mTextView = (TextView) findViewById(R.id.tv_title);

        for(int i = 0; i<mData.size();i++){
            Picasso.with(this).load(mData.get(i).imageUrl).into(mImageView);
            mTextView.setText(mData.get(i).title);
        }

        //   Url = getIntent().getExtras().getString("content");
 //       Html.fromHtml(Url);
//       mWebView.loadUrl(Url);
        loaderData();
    }
    private void loaderData(){
        VolleyUtils.newInstance(this).newGsonRequest(Request.Method.GET,Url, new Response.Listener<ContentBean>() {
            @Override
            public void onResponse(ContentBean response) {
                if(response!=null){
                    mData.add(response.body);
                }
            }
        },null,ContentBean.class);
    }
}
