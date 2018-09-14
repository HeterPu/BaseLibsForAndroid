package com.heterpu.phbaselib.ui.vlayouthelper;

import android.support.annotation.NonNull;

import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.heterpu.phbaselib.ui.uicollectionview.UICol_Beans;
;
import java.util.List;


/**
 *   For vLayoutAdapter associate with VirtualBean
 *
 * @param <T> Model extends MultiItemEntity
 * @param <K> ViewHolder extends BaseViewHolder
 */
public abstract  class VirtualHelperAdapter<T extends MultiItemEntity,K extends BaseViewHolder> extends BaseMultiItemQuickAdapter<T,K> {


    /**
     * @param layoutManager Virtual layout manager
     * @param data data List
     */
    public VirtualHelperAdapter(@NonNull VirtualLayoutManager layoutManager,List<T> data){
        super(data);

        // Bind viewType and resourceId.
        List<UICol_Beans.ResourceBind> list = getResourceBind();
        for (UICol_Beans.ResourceBind bean:list){
            addItemType(bean.getTypeId(),bean.getResourceId());
        }
    }


    /**
     * @return  Register and Bind itemType with resourceId.
     */
    public abstract List<UICol_Beans.ResourceBind> getResourceBind();

}
