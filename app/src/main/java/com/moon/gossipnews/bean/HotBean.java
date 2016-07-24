package com.moon.gossipnews.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/7/20.
 */
public class HotBean {
    public bodyBean body;
    public class bodyBean{
        public List<DataBean> datas;
    }
    public class DataBean{
        public String imageUrl;
        public String summary;
        public String title;
        public String agreementCount;
        public String readCount;
    }
}
