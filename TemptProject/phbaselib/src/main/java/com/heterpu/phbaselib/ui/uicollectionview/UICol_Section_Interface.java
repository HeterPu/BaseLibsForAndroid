package com.heterpu.phbaselib.ui.uicollectionview;

import android.graphics.Rect;
import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 *  PETER'S COPYRIGHT RESERVED.
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



    Rect getItemCacheRect();
    void setItemCacheRect(Rect itemCache);

}
