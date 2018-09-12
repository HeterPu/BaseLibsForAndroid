package com.heterpu.phbaselib.ui.uicollectionview;

import android.graphics.Rect;
import com.chad.library.adapter.base.entity.MultiItemEntity;


/**
 *
 */
public interface UICol_Section_Interface extends MultiItemEntity {

    /**
     * Section Type
     */
    enum SectionType {
        HEADER,ITEM,FOOTER;
    }


    /**
     *  Configure IndexPath sectionIndex and itemIndex
     */
    int getSectionIndex();
    int getItemIndex();
    void setSectionIndex(int sectionIndex);
    void setItemIndex(int itemIndex);


    /**
     *  Configure SpanCount
     */
    int getSpanCount();


    /**
     *  Configure SectionType
     */
    SectionType getSectionType();
    void setSectionType(SectionType type);


    int  getLayoutResourceId();


    /**
     * For cache item calculation.
     */
    void setItemCacheRect(Rect itemCache);
    Rect getItemCacheRect();

}
