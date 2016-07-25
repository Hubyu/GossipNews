package com.moon.gossipnews.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.moon.gossipnews.R;
import com.moon.gossipnews.bean.HotBean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2016/7/20.
 */
public class HotAdapter extends BaseAdapter{

    private Context mContext;
    private List<HotBean.DataBean> mData;
    private DisplayImageOptions mOption;
    public HotAdapter(Context context, List<HotBean.DataBean> data){
        this.mContext = context;
        this.mData = data;
        mOption = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .showImageOnLoading(R.mipmap.disclose_default_photo)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
    }

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
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
        HotHolder holder = null;
        if(convertView==null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_hot,parent,false);
            holder = new HotHolder(convertView);
            convertView.setTag(holder);
        }else{
           holder = (HotHolder) convertView.getTag();
        }
        holder.tv_title.setText(mData.get(position).title);
        holder.tv_desc.setText(mData.get(position).summary);
        holder.tv_readcount.setText(mData.get(position).readCount);
        holder.tv_agreecount.setText(mData.get(position).agreementCount);
        ImageLoader.getInstance().displayImage(mData.get(position).imageUrl,holder.imageView,mOption);

        return convertView;
    }

    class HotHolder {
        private ImageView imageView;
        private TextView tv_title, tv_desc, tv_agreecount, tv_readcount;
        public HotHolder(View itemView){
            imageView = (ImageView) itemView.findViewById(R.id.iv_hot);
            tv_title = (TextView) itemView.findViewById(R.id.tv_hot_title);
            tv_desc = (TextView) itemView.findViewById(R.id.tv_hot_desc);
            tv_agreecount = (TextView) itemView.findViewById(R.id.tv_agreecount);
            tv_readcount = (TextView) itemView.findViewById(R.id.tv_readcont);
        }
    }
}
