package com.moon.gossipnews.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/7/20.
 */
public class ActivityBean {
    public BodyBean body;
    public class BodyBean{
        public List<DatasBean> datas;
    }
    public class DatasBean{
        public String imgurl;
        public String title;
        public String detail;
    }
}
