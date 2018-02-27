package com.cdkj.myxb.adapters;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import com.cdkj.baselibrary.utils.DateUtil;
import com.cdkj.myxb.R;
import com.cdkj.myxb.models.MouthAppointmentModel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 行程日历
 * Created by cdkj on 2017/10/12.
 */

public class TripDateAdapter extends BaseQuickAdapter<MouthAppointmentModel, BaseViewHolder> {


    public TripDateAdapter(@Nullable List<MouthAppointmentModel> data) {
        super(R.layout.item_trip_date, data);
    }


    @Override
    protected void convert(BaseViewHolder viewHolder, MouthAppointmentModel item) {
        if (item == null) {
            viewHolder.setText(R.id.tv_data_sign, "");
            viewHolder.setBackgroundColor(R.id.tv_data_sign, ContextCompat.getColor(mContext, R.color.white));
            return;
        }
        viewHolder.setText(R.id.tv_data_sign, DateUtil.format(item.getDate(), "dd"));

        if (item.isSame()) {
            viewHolder.setBackgroundRes(R.id.tv_data_sign, R.drawable.trip_date_select);
            viewHolder.setTextColor(R.id.tv_data_sign, ContextCompat.getColor(mContext, R.color.white));

        } else {
            viewHolder.setBackgroundRes(R.id.tv_data_sign, 0);
            viewHolder.setTextColor(R.id.tv_data_sign, ContextCompat.getColor(mContext, R.color.app_txt_black));
        }

    }


}
