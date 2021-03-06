package com.cdkj.myxb.adapters;

import android.support.annotation.Nullable;

import com.cdkj.baselibrary.appmanager.SPUtilHelpr;
import com.cdkj.baselibrary.utils.DateUtil;
import com.cdkj.baselibrary.utils.ImgUtils;
import com.cdkj.baselibrary.utils.MoneyUtils;
import com.cdkj.myxb.R;
import com.cdkj.myxb.models.BrandProductModel;
import com.cdkj.myxb.models.TripListModel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.Date;
import java.util.List;

/**
 * 行程列表
 * Created by cdkj on 2017/10/12.
 */

public class TripListAdapter extends BaseQuickAdapter<TripListModel, BaseViewHolder> {


    public TripListAdapter(@Nullable List<TripListModel> data) {
        super(R.layout.item_trip, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TripListModel item) {
        if (item == null) return;


        ImgUtils.loadQiniuLogo(mContext, SPUtilHelpr.getUserPhoto(), helper.getView(R.id.img_logo));

        helper.setText(R.id.tv_date, DateUtil.formatStringData(item.getStartDatetime(), DateUtil.DEFAULT_DATE_FMT) + "-" + DateUtil.formatStringData(item.getEndDatetime(), DateUtil.DEFAULT_DATE_FMT));


    }
}
