package com.fiction.fiction.home.widget;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.ViewGroup;

import com.fiction.R;
import com.fiction.fiction.contents.widget.FictionContentsActivity;
import com.fiction.fiction.home.model.FictionHomeModel;
import com.fiction.manager.ApiConfig;
import com.fiction.manager.JsoupFictionHomeManager;
import com.framework.base.SuperViewHolder;
import com.framework.utils.ImageLoaderUtils;
import com.framework.utils.UIUtils;
import com.rxjsoupnetwork.bus.RxBus;

import java.util.List;

/**
 * by y on 2017/4/21
 */

class FictionHomeAdapter extends RecyclerView.Adapter<SuperViewHolder> {
    private List<FictionHomeModel> list = null;
    private String type = ApiConfig.Type.ZW_81;

    FictionHomeAdapter(List<FictionHomeModel> list, String type) {
        this.list = list;
        this.type = type;
    }

    @Override
    public SuperViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case JsoupFictionHomeManager.TYPE_HEADER:
                return new SuperViewHolder(UIUtils.getAdapterView(parent, R.layout.item_fiction_home_header));
            case JsoupFictionHomeManager.TYPE_TITLE:
                return new SuperViewHolder(UIUtils.getAdapterView(parent, R.layout.item_fiction_home_title));
            default:
                return new SuperViewHolder(UIUtils.getAdapterView(parent, R.layout.item_fiction_home_item));
        }
    }

    @Override
    public void onBindViewHolder(SuperViewHolder holder, int position) {
        if (list == null) {
            return;
        }
        FictionHomeModel kswHomeModel = list.get(position);
        switch (getItemViewType(position)) {
            case JsoupFictionHomeManager.TYPE_HEADER:

                holder.setTextView(R.id.tv_title, kswHomeModel.title);
                holder.setTextView(R.id.tv_content, kswHomeModel.message);
                ImageLoaderUtils.display(holder.getImageView(R.id.image), kswHomeModel.url);
                holder.itemView.setOnClickListener(v -> FictionContentsActivity.getInstance(type, kswHomeModel.detailUrl, kswHomeModel.title));

                break;
            case JsoupFictionHomeManager.TYPE_TITLE:
                holder.setTextView(R.id.tv_title, kswHomeModel.title);
                holder.itemView.setOnClickListener(v -> RxBus.getInstance().send(ApiConfig.Type.BI_QU_GE, kswHomeModel.title));
                break;
            case JsoupFictionHomeManager.TYPE_HOT:
            case JsoupFictionHomeManager.TYPE_CENTER:
            case JsoupFictionHomeManager.TYPE_RECENT:
            case JsoupFictionHomeManager.TYPE_ADD:
                holder.setTextView(R.id.tv_title, kswHomeModel.title);
                holder.itemView.setOnClickListener(v -> FictionContentsActivity.getInstance(type, kswHomeModel.detailUrl, kswHomeModel.title));
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).type;
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    void addAll(List<FictionHomeModel> list) {
        if (this.list != null) {
            this.list.addAll(list);
            notifyDataSetChanged();
        }
    }

    void removeAll() {
        if (list != null) {
            list.clear();
            notifyDataSetChanged();
        }
    }

    @Override
    public void onViewAttachedToWindow(SuperViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        if (layoutParams != null && layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
            StaggeredGridLayoutManager.LayoutParams stagger = (StaggeredGridLayoutManager.LayoutParams) layoutParams;
            int itemViewType = getItemViewType(holder.getLayoutPosition());
            if (itemViewType == JsoupFictionHomeManager.TYPE_HEADER
                    || itemViewType == JsoupFictionHomeManager.TYPE_TITLE) {
                stagger.setFullSpan(true);
            } else {
                stagger.setFullSpan(false);
            }
        }
    }
}