package com.fiction.mvp.model;

import com.framework.base.mvp.BaseModel;
import com.xadapter.adapter.multi.MultiCallBack;

import java.util.Objects;

/**
 * by y on 2017/4/6.
 */

public class FictionModel extends BaseModel implements MultiCallBack {
    public String nextPage;
    public String onPage;
    public int type = -1;

    @Override
    public int getItemType() {
        return type;
    }

    @Override
    public int getPosition() {
        return -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FictionModel that = (FictionModel) o;
        return detailUrl.equals(that.detailUrl);
    }
}
