package com.heterpu.phbaselib.ui.uicollectionview;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;


import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;


import java.util.ArrayList;
import java.util.List;


/**
 *  PETER'S COPYRIGHT RESERVED.
 */

public abstract class UICol_Adapter<T extends UICol_Section_Interface, K extends BaseViewHolder> extends BaseMultiItemQuickAdapter<T,K> {


    /**
     * Display Type
     */
    public enum DisplayType {
        NORMAL,MIX,WATER_FLOW
    }

    /**
     *  Corresponding Two-dimensional to mData.
     */
    private List<List<T>> mSDData = new ArrayList<>();


    /**
     */
    public  UICol_Adapter(){
        super(new ArrayList<T>());
        reloadData();
    }


    public void reloadData() {
        // When data more than 0 , begin loading and set style.
        mData.clear();
        mSDData.clear();

        int section = numberOfSectionInView();
        for (int i = 0; i < section; i++) {
            List<T> sectionArra = new ArrayList<>();
            //Add header
            T headerBean = modelForHeaderFooterInSection(UICol_Section_Interface.SectionType.HEADER, i);
            if (headerBean != null) {
                headerBean.setSectionIndex(i);
                headerBean.setItemIndex(-1);
                headerBean.setSectionType(UICol_Section_Interface.SectionType.HEADER);
                mData.add(headerBean);
                sectionArra.add(headerBean);
                addItemType(headerBean.getItemType(), headerBean.getLayoutResourceId());
            }

            // Add Corresponding Item in Section
            int itemCount = numberOfItemInSection(UICol_Section_Interface.SectionType.ITEM, i);
            for (int j = 0; j < itemCount; j++) {
                T itemBean = modelForItemIndexPath(new UICol_Beans.IndexPath(i, j));
                itemBean.setSectionType(UICol_Section_Interface.SectionType.ITEM);
                itemBean.setSectionIndex(i);
                itemBean.setItemIndex(j);
                mData.add(itemBean);
                sectionArra.add(itemBean);
                addItemType(itemBean.getItemType(), itemBean.getLayoutResourceId());
            }

            // Add footer
            T footerBean = modelForHeaderFooterInSection(UICol_Section_Interface.SectionType.FOOTER, i);
            if (footerBean != null) {
                footerBean.setSectionType(UICol_Section_Interface.SectionType.FOOTER);
                footerBean.setSectionIndex(i);
                footerBean.setItemIndex(-1);
                mData.add(footerBean);
                sectionArra.add(footerBean);
                addItemType(footerBean.getItemType(), footerBean.getLayoutResourceId());
            }
            if (0 != sectionArra.size()) mSDData.add(sectionArra);
        }


        setSpanSizeLookup(new SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                T model = mData.get(position);
                return model.getSpanCount();
            }
        });

    }


    /**
     * @param position Item Position IN ADAPTER
     * @return A rect for setting ItemDecoration , default sectionType (HEADER FOOTER) Return Rect(0,0,0,0)
     */
    public Rect getItemRectWithPosition(int position){

          T model;
          if(getHeaderLayout() != null){
              if (position <= 0)return new  Rect(0,0,0,0);
              if (mData.size() < position)  return new Rect(0,0,0,0);
              model = mData.get(position - 1);
          }else {
             if (mData.size() <= position)return new Rect(0,0,0,0);
             model  = mData.get(position);
          }
        if (model.getSectionType() == UICol_Section_Interface.SectionType.ITEM){

              // Cache item Calculation
              if (allowUseCacheItemsCalculation()){
                  Rect rect = model.getItemCacheRect();
                  if (rect != null) return rect;
              }
            if (getDisplayType() == DisplayType.NORMAL) {
                // Judge if section has footer to get right item count
                List<T> modelArra = mSDData.get(model.getSectionIndex());
                int totalCount = modelArra.size();
                if (modelArra.get(modelArra.size() - 1).getSectionType() == UICol_Section_Interface.SectionType.FOOTER) {
                    totalCount = modelArra.get(modelArra.size() - 2).getItemIndex() + 1;
                }

                int columnCount = UICol_Beans.recommendedSpanCount / model.getSpanCount();
                int rowCount = (totalCount - 1) / columnCount;

                int currentColumn = model.getItemIndex() % columnCount;
                int currentRow = model.getItemIndex() / columnCount;

                int topPadding = 0;
                int leftPadding = 0;
                int rightPadding = minimumInteritemSpacingForSection(model.getSectionIndex());
                int bottomPadding = minimiumLineSpacingForSection(model.getSectionIndex());

                Rect sectionInset = sectionInsetForSection(model.getSectionIndex());
                if(currentRow == 0) topPadding = sectionInset.top;
                if (currentColumn == 0)leftPadding = sectionInset.left;
                if (currentColumn == columnCount - 1)rightPadding = sectionInset.right;
                if (currentRow == rowCount)bottomPadding = sectionInset.bottom;


                Rect newRect = new Rect(leftPadding,topPadding,rightPadding,bottomPadding);
                if (allowUseCacheItemsCalculation()){
                    model.setItemCacheRect(newRect);
                }
                return newRect;
            }else{
                return new Rect(0,0,0,0);
            }
        }else{
            return new Rect(0,0,0,0);
        }
    }



    protected DisplayType getDisplayType(){
        return DisplayType.NORMAL;
    }

    /**
     * @return GET SECTION COUNT
     */
    protected int numberOfSectionInView(){
        // 通过获取最后的section值来获取section个数
        return 0;
    }

    protected int numberOfItemInSection(UICol_Section_Interface.SectionType sectionType, int section){
        return 0;
    }

    protected T modelForHeaderFooterInSection(UICol_Section_Interface.SectionType sectionType,int section){
        return null;
    }


    protected T modelForItemIndexPath(UICol_Beans.IndexPath indexPath){
        return null;
    }


    protected int minimiumLineSpacingForSection(int section){
        return 0;
    }


    protected int minimumInteritemSpacingForSection(int section){
        return 0;
    }

    protected Rect sectionInsetForSection(int section){
        return   new Rect(0,0,0,0);
    }


    protected void convertItemAtIndexPath(UICol_Section_Interface.SectionType sectionType, UICol_Beans.IndexPath indexpath,K helper,T data){

    }


    protected void convertHeaderFooterAtSection(UICol_Section_Interface.SectionType sectionType,int section,K helper,T data){

    }


    protected boolean allowUseCacheItemsCalculation(){
        return true;
    }

    @Override
    protected void convert(K helper, T item) {
        int sectionIndex = item.getSectionIndex();
        int itemIndex = item.getItemIndex();
        List<T> dataL = mSDData.get(sectionIndex);
        UICol_Section_Interface.SectionType type = item.getSectionType();
        if (UICol_Section_Interface.SectionType.HEADER == type){
            T data = dataL.get(0);
          convertHeaderFooterAtSection(UICol_Section_Interface.SectionType.HEADER,sectionIndex,helper,data);
        }else if (UICol_Section_Interface.SectionType.FOOTER == type) {
            T data = dataL.get(dataL.size() - 1);
            convertHeaderFooterAtSection(UICol_Section_Interface.SectionType.FOOTER,sectionIndex,helper,data);
        }else {
            T modelData = dataL.get(0);
            T data = dataL.get(itemIndex);
            if (modelData.getSectionType() == UICol_Section_Interface.SectionType.HEADER)data = dataL.get(itemIndex + 1);
            convertItemAtIndexPath(UICol_Section_Interface.SectionType.ITEM, new UICol_Beans.IndexPath(sectionIndex,itemIndex),helper,data);
        }
    }


}
