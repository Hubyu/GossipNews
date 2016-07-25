package com.moon.gossipnews.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/7/20.
 */
public class BameiBean {
    public BodyBean body;
    public class BodyBean{
        public List<DatasBean> datas;
    }
    public class DatasBean{
        public String askUserAvatar;
        public String askUserName;
        public String askTitle;
        public String answer;
    }
}
