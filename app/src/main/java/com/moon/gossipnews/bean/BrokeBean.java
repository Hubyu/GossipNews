package com.moon.gossipnews.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/7/20.
 */
public class BrokeBean {
    public DataBody body;
    public class DataBody{
        public List<DatasBean> datas;

    }
    public class DatasBean{
        public String title;
        public String readCount;
        public String commentCount;
        public List<String> images;
    }
}
