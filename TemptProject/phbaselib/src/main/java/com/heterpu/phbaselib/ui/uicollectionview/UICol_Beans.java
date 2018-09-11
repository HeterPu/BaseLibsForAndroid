package com.heterpu.phbaselib.ui.uicollectionview;

import android.graphics.Rect;


/**
 *  UICol_Beans deal With Section logic.
 *
 *  PETER'S COPYRIGHT RESERVED.
 */

public abstract class UICol_Beans implements UICol_Section_Interface {


    /**
     * Almost the Least Common Multiple of (1 - 10) to satisfied common demand.
     */
    public  static final int recommendedSpanCount = 2520;


    public  UICol_Beans(int itemType,int resourceId, int spanCount){
        super();
        mItemType = itemType;
        mResourceId = resourceId;
        mSpanCount = spanCount;
    }


    /**
     * Flag cell type
     */
    private int mItemType;
    private  int mSpanCount;
    private Rect mItemCache;

    @Override
    public int getItemType() {
        return mItemType;
    }

    @Override
    public int getSpanCount() {
        return mSpanCount;
    }


    @Override
    public Rect getItemCacheRect() {
        return mItemCache;
    }


    @Override
    public void setItemCacheRect(Rect itemCache) {
        this.mItemCache = itemCache;
    }

    /**
     * Implement UICol_Section_Interface
     */

    private int mSectionIndex;
    private int mItemIndex;
    private SectionType mSectionType;
    private  int mResourceId;

    @Override
    public int getSectionIndex() {
        return mSectionIndex;
    }

    @Override
    public void setSectionIndex(int sectionIndex) {
        this.mSectionIndex = sectionIndex;
    }

    @Override
    public void setItemIndex(int itemIndex) {
        this.mItemIndex = itemIndex;
    }

    @Override
    public int getItemIndex() {
        return mItemIndex;
    }

    @Override
    public SectionType getSectionType() {
        return mSectionType;
    }

    @Override
    public void setSectionType(SectionType type) {
        this.mSectionType = type;
    }

    @Override
    public int getLayoutResourceId() {
        return mResourceId;
    }



    /**
     *  Other beans
     */


    public static class ResourceBind{
        public int getTypeId() {
            return typeId;
        }

        public void setTypeId(int typeId) {
            this.typeId = typeId;
        }

        public int getResourceId() {
            return resourceId;
        }

        public void setResourceId(int resourceId) {
            this.resourceId = resourceId;
        }

        int typeId;
        int resourceId;
    }


   public static class IndexPath{
       private int section;
       private int itemIndex;


       public  IndexPath(int section, int itemIndex){
           this.section = section;
           this.itemIndex = itemIndex;
       }


        public int getSection() {
            return section;
        }

        public void setSection(int section) {
            this.section = section;
        }

        public int getItemIndex() {
            return itemIndex;
        }

        public void setItemIndex(int itemIndex) {
            this.itemIndex = itemIndex;
        }




    }
}
