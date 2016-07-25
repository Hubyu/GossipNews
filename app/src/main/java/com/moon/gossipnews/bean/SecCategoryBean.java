package com.moon.gossipnews.bean;

import java.util.List;

/**
 * Created by 王祯 on 2016/7/24.
 */
public class SecCategoryBean {
    public ContentBean body;
    public class ContentBean{
        public List<DetailBean> datas;
        public String pageSize;
        public String totalCount;
        public String totalPages;
        public class DetailBean{
            public String title;
            public String summary;
            public String imageUrl;
        }
    }
}
