package com.moon.gossipnews.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.moon.gossipnews.R;
import com.moon.gossipnews.bean.BrokeBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/20.
 */
public class LvBrokeAdapter extends BaseAdapter {
    private ArrayList<BrokeBean.DatasBean> data;
    private Context mContext;
    public LvBrokeAdapter(Context context,ArrayList<BrokeBean.DatasBean> data){
        this.data=data;
        this.mContext=context;

    }

    @Override
    public int getCount() {
        return data == null ? 0:data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            convertView= LayoutInflater.from(mContext).inflate(R.layout.broke_list_item,parent,false);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        BrokeBean.DatasBean datasBean = data.get(position);
        if(datasBean.images==null){
            holder.iv_min_broke.setImageResource(R.mipmap.icon_join_default);
        }else{
            ImageLoader.getInstance().displayImage(datasBean.images.get(0),holder.iv_min_broke);
        }
        holder.tv_broke_title.setText(datasBean.title);
        holder.tv_broke_look.setText(datasBean.readCount);
        holder.tv_broke_comment.setText(datasBean.commentCount);
        return convertView;
    }
    class ViewHolder{
        private ImageView iv_min_broke;
        private TextView tv_broke_title;
        private TextView tv_broke_look;
        private TextView tv_broke_comment;
        public ViewHolder(View itemView){
            iv_min_broke= (ImageView) itemView.findViewById(R.id.iv_min_broke);
            tv_broke_title= (TextView) itemView.findViewById(R.id.tv_broke_title);
            tv_broke_look= (TextView) itemView.findViewById(R.id.tv_broke_look);
            tv_broke_comment= (TextView) itemView.findViewById(R.id.tv_broke_comment);
        }
    }
}
