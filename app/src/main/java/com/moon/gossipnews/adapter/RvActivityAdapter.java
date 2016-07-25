package com.moon.gossipnews.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.moon.gossipnews.R;
import com.moon.gossipnews.bean.ActivityBean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2016/7/20.
 */
public class RvActivityAdapter extends RecyclerView.Adapter<RvActivityAdapter.ActivityHolder> {
    private Context mContext;
    private List<ActivityBean.DatasBean> mDatas;
    private final DisplayImageOptions mOptions;
    public RvActivityAdapter(Context context,List<ActivityBean.DatasBean> datas){
        this.mContext=context;
        this.mDatas=datas;
        mOptions=new DisplayImageOptions.Builder()
                .showImageOnFail(R.mipmap.icon_join_default)
                .showImageOnLoading(R.mipmap.icon_join_default)
                .cacheOnDisk(true)
                .cacheInMemory(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
    }
    @Override
    public ActivityHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.item_activity,parent,false);
        return new ActivityHolder(view);
    }

    @Override
    public void onBindViewHolder(ActivityHolder holder, int position) {
        ActivityBean.DatasBean datasBean = mDatas.get(position);
        ImageLoader.getInstance().displayImage(datasBean.imgurl, holder.iv_activity_imgurl, mOptions);
        holder.tv_activity_title.setText(datasBean.title);
        holder.tv_activity_detail.setText(datasBean.detail);
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    public class ActivityHolder extends RecyclerView.ViewHolder {
        private ImageView iv_activity_imgurl;
        private TextView tv_activity_title;
        private TextView tv_activity_detail;
        public ActivityHolder(View itemView) {
            super(itemView);
            iv_activity_imgurl= (ImageView) itemView.findViewById(R.id.iv_activity_imgurl);
            tv_activity_title= (TextView) itemView.findViewById(R.id.tv_activity_title);
            tv_activity_detail= (TextView) itemView.findViewById(R.id.tv_activity_detail);
        }
    }
}
