package com.moon.gossipnews.adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.moon.gossipnews.R;
import com.moon.gossipnews.bean.SecCategoryBean;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by 王祯 on 2016/7/24.
 */
public class SecCateAdapter extends BaseAdapter {
    private Context mContext;
    private List<SecCategoryBean.ContentBean.DetailBean> mDatas;
    public SecCateAdapter(Context context, List<SecCategoryBean.ContentBean.DetailBean> datas){
        this.mContext=context;
        this.mDatas=datas;
    }
    @Override
    public int getCount() {
        return mDatas==null?0:mDatas.size();
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
        ViewHolder mViewHolder=null;
        View view= LayoutInflater.from(mContext).inflate(R.layout.category_second,parent,false);
        mViewHolder=new ViewHolder(view);
        Picasso.with(mContext).load(mDatas.get(position).imageUrl).placeholder(R.mipmap.disclose_default_photo).into(mViewHolder.iv_image);
        mViewHolder.tv_title.setText(mDatas.get(position).title);
        mViewHolder.tv_summary.setText(mDatas.get(position).summary);
        return view;
    }
    class ViewHolder{
        private ImageView iv_image;
        private TextView tv_title;
        private TextView tv_summary;
        public ViewHolder(View itemView){
            iv_image= (ImageView) itemView.findViewById(R.id.iv_image);
            tv_title= (TextView) itemView.findViewById(R.id.tv_title);
            tv_summary= (TextView) itemView.findViewById(R.id.tv_summary);
        }
    }
}
