package com.heterpu.phbaselib.ui.uicollectionview;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import java.util.ArrayList;
import java.util.List;


/**
 *  Adapter can be subclassing, Easy to use，just like UICollectionView.
 * @param <T> Class which implement UICol_Section_Interface.
 * @param <K> Class which implement BaseViewHolder.
 */

public abstract class UICol_Adapter<T extends UICol_Section_Interface, K extends BaseViewHolder> extends BaseMultiItemQuickAdapter<T,K> {


    /**
     * Display Type
     * Normal -- normal grid layout
     * Mix -- Complex layout
     * WATER_FLOW  -- WaterFlow layout
     */
    public enum DisplayType {
        NORMAL,MIX,WATER_FLOW
    }

    /**
     *  Corresponding Two-dimensional to mData.
     */
    private List<List<T>> mSDData = new ArrayList<>();


    /**
     * Construction method for initialization.
     */
    public  UICol_Adapter(){
        super(new ArrayList<T>());
        reloadData();
    }


    /**
     *  Empty all data and  reload all data.
     */
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
     * Reload specified section in recyclerView.
     * @param section section you want to reload.
     */
    public void reloadDataForSection(int section){
        // Continuing
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
                int rightPadding = minimumInterItemSpacingForSection(model.getSectionIndex());
                int bottomPadding = minimumLineSpacingForSection(model.getSectionIndex());

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




    // ------------------ Data Sources Start -------------------------------------


    /**
     * @return Overridng by subclass to let adapter judge which layout to choose.
     * Default is  DisplayType.NORMAL.
     */
    protected DisplayType getDisplayType(){
        return DisplayType.NORMAL;
    }


    /**
     * @return Get section Count in a Layout.
     */
    protected int numberOfSectionInView(){
        return 0;
    }


    /**
     * @param sectionType sectionType in a Layout.
     * @param section designated section.
     * @return Get item Count in a specified Layout.
     */
    protected int numberOfItemInSection(UICol_Section_Interface.SectionType sectionType, int section){
        return 0;
    }


    /**
     * @param sectionType  sectionType in a Layout.
     * @param section  designated section.
     * @return Class which implement UICol_Section_Interface.
     */
    protected T modelForHeaderFooterInSection(UICol_Section_Interface.SectionType sectionType,int section){
        return null;
    }


    /**
     * @param indexPath the specified position in Layout.
     * @return  Class which implement UICol_Section_Interface.
     */
    protected T modelForItemIndexPath(UICol_Beans.IndexPath indexPath){
        return null;
    }


    /**
     * @param section  designated section
     * @return  get the minimum Line Spacing in designated section.
     */
    protected int minimumLineSpacingForSection(int section){
        return 0;
    }


    /**
     * @param section  designated section.
     * @return  get the minimum Inter Item in designated section.
     */
    protected int minimumInterItemSpacingForSection(int section){
        return 0;
    }


    /**
     * @param section  designated section.
     * @return  get section inset in designated section.
     */
    protected Rect sectionInsetForSection(int section){
        return   new Rect(0,0,0,0);
    }


    /**
     * Bind item data.
     * @param sectionType  sectionType in a Layout.
     * @param indexpath  the specified position in Layout.
     * @param helper helper for binding data.
     * @param data data to bind.
     */
    protected void convertItemAtIndexPath(UICol_Section_Interface.SectionType sectionType, UICol_Beans.IndexPath indexpath,K helper,T data){

    }


    /**
     * Bind header footer data.
     * @param sectionType  sectionType in a Layout.
     * @param section the specified section in Layout.
     * @param helper helper for binding data.
     * @param data  data to bind.
     */
    protected void convertHeaderFooterAtSection(UICol_Section_Interface.SectionType sectionType,int section,K helper,T data){

    }


    /**
     * @return allow use item calculation to make complex layout smooth. Default is true.
     */
    protected boolean allowUseCacheItemsCalculation(){
        return true;
    }


    // ------------------ Data Sources End -------------------------------------



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
