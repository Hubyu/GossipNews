package com.moon.gossipnews.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.moon.gossipnews.R;
import com.moon.gossipnews.bean.BameiBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Administrator on 2016/7/20.
 */
public class LvBameiAdapter extends BaseAdapter {
    private List<BameiBean.DatasBean> data;
    private Context mContext;

    public LvBameiAdapter(Context context, List<BameiBean.DatasBean> list) {
        this.mContext = context;
        this.data = list;
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
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
        BameiHolder bameiHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.bamei_list_item, parent, false);
            bameiHolder=new BameiHolder(convertView);
            convertView.setTag(bameiHolder);
        }else{
            bameiHolder= (BameiHolder) convertView.getTag();
        }
        BameiBean.DatasBean datasBean = data.get(position);
        if(datasBean.askUserAvatar==null){
            bameiHolder.iv_bamei_avater.setImageResource(R.mipmap.icon_join_default);
        }else{
            ImageLoader.getInstance().displayImage(datasBean.askUserAvatar,bameiHolder.iv_bamei_avater);
        }
        bameiHolder.tv_bamei_username.setText(datasBean.askUserName);
        bameiHolder.tv_bamei_title.setText(datasBean.askTitle);
        bameiHolder.tv_bamei_answer.setText(datasBean.answer);
        return convertView;
    }

    class BameiHolder {
        private ImageView iv_bamei_avater;
        private TextView tv_bamei_username;
        private TextView tv_bamei_title;
        private TextView tv_bamei_answer;

        public BameiHolder(View itemView) {
            iv_bamei_avater = (ImageView) itemView.findViewById(R.id.iv_bamei_avater);
            tv_bamei_username= (TextView) itemView.findViewById(R.id.tv_bamei_username);
            tv_bamei_title= (TextView) itemView.findViewById(R.id.tv_bamei_title);
            tv_bamei_answer= (TextView) itemView.findViewById(R.id.tv_bamei_answer);
        }
    }
}
