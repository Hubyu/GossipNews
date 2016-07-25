package com.moon.gossipnews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.moon.gossipnews.R;
import com.moon.gossipnews.bean.CategoryBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by 王祯 on 2016/7/20.
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private  Context mContext;
   private List<CategoryBean.DataBean> mDatas;
    public CategoryAdapter(Context context,List<CategoryBean.DataBean> datas){
        this.mContext=context;
        this.mDatas=datas;
    }
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder mViewHolder=null;
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_loadmore,parent,false);
        mViewHolder=new ViewHolder(view);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(CategoryAdapter.ViewHolder holder, int position) {

        Picasso.with(mContext).load(mDatas.get(position).topicVo.image).into(holder.iv_item);

    }

    @Override
    public int getItemCount() {
        return mDatas==null?0:mDatas.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_item;
        public ViewHolder(View itemView) {
            super(itemView);
            iv_item= (ImageView) itemView.findViewById(R.id.iv_item);
        }
    }


}
