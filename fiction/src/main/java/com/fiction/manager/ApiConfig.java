package com.fiction.manager;

/**
 * by y on 2017/3/25.
 */

public interface ApiConfig {

    String ON_PAGE = "上一章";
    String NEXT_PAGE = "下一章";

    //搜索
    String SEARCH_URL = "https://www.zwdu.com/search.php?keyword=";

    //81中文网
    String ZW81_URL = "https://www.zwdu.com/";

    //笔趣阁
    String BI_QU_GE_URL = "https://www.biqiuge.com/";

    //飘天文学
    String PIAO_TIAN_URL = "https://www.piaotian.com/";

    interface Type {
        String ZW_81 = "81";
        String BI_QU_GE = "笔趣阁";
        String PIAO_TIAN = "飘天文学";
    }
}
