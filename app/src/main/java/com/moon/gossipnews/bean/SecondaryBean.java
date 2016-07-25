package com.moon.gossipnews.bean;

import java.util.List;

/**
 * Created by 王祯 on 2016/7/21.
 */
public class SecondaryBean {
    public BodyBean body;
    public class BodyBean{
        public List<DataBean> datas;
        public  String pagesize;
        public  String totalCount;
        public String totalPages;


    }
    public class DataBean{
        public TopicVoBean topicVo;

    }
    public class TopicVoBean{
        public  String title;
        public String image;
    }
}
