package com.cdkj.myxb.module.order;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.cdkj.baselibrary.appmanager.MyCdConfig;
import com.cdkj.baselibrary.base.AbsBaseLoadActivity;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.DateUtil;
import com.cdkj.baselibrary.utils.ImgUtils;
import com.cdkj.baselibrary.utils.MoneyUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.myxb.R;
import com.cdkj.myxb.databinding.ActivityIntegralOrderDetailsBinding;
import com.cdkj.myxb.models.CommentCountAndAverage;
import com.cdkj.myxb.models.OrderListModel;
import com.cdkj.myxb.module.api.MyApiServer;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;

/**
 * 订单详情
 * Created by 李先俊 on 2018/2/23.
 */

public class OrderDetailsActivity extends AbsBaseLoadActivity {

    private ActivityIntegralOrderDetailsBinding mBinding;

    private String mOrderCode;

    private static final String ORDERCODE = "code";

    private String mOrderState; //订单状态

    /**
     * @param context
     * @param orderCode 订单编号
     */
    public static void open(Context context, String orderCode) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, OrderDetailsActivity.class);
        intent.putExtra(ORDERCODE, orderCode);
        context.startActivity(intent);
    }


    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_integral_order_details, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        mBaseBinding.titleView.setMidTitle("订单详情");

        if (getIntent() != null) {
            mOrderCode = getIntent().getStringExtra(ORDERCODE);
        }

        mBinding.btnStateDo.setOnClickListener(view -> {
            OrderCommentActivity.open(this, mOrderCode);
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        getOrderDetailsRequest();
    }

    /**
     * 获取订单详情
     */
    public void getOrderDetailsRequest() {

        if(TextUtils.isEmpty(mOrderCode)) return;

        Map<String, String> map = new HashMap<>();

        map.put("code", mOrderCode);

        showLoadingDialog();

        Call call = RetrofitUtils.createApi(MyApiServer.class).getOrderDetails("805275", StringUtils.getJsonToString(map));

        call.enqueue(new BaseResponseModelCallBack<OrderListModel>(this) {
            @Override
            protected void onSuccess(OrderListModel data, String SucMessage) {
                mBaseBinding.contentView.setShowText(null);
                setShowData(data);
            }

            @Override
            protected void onReqFailure(String errorCode, String errorMessage) {
                super.onReqFailure(errorCode, errorMessage);
                mBaseBinding.contentView.setShowText(errorMessage);
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });


    }



    /**
     * 设置数据
     *
     * @param data
     */
    private void setShowData(OrderListModel data) {

        if (data == null) return;

        mOrderState = data.getStatus();

        ImgUtils.loadImg(this, MyCdConfig.QINIUURL + data.getProductPic(), mBinding.headerLayout.imgGood);

        //订单信息
        mBinding.headerLayout.tvOrderName.setText(data.getProductSlogan());
        mBinding.tvName.setText(data.getProductName());
        mBinding.headerLayout.tvPrice.setText(MoneyUtils.getShowPriceSign(data.getAmount()));
        mBinding.tvPrice.setText(MoneyUtils.getShowPriceSign(data.getAmount()));
        mBinding.headerLayout.tvNum.setText("X" + data.getQuantity());
        mBinding.tvNum.setText("" + data.getQuantity());
        mBinding.tvOrderCode.setText(data.getCode());
        mBinding.tvState.setText(OrderHelper.getOrderState(data.getStatus()));
        mBinding.tvOrderTime.setText(DateUtil.formatStringData(data.getApplyDatetime(), DateUtil.DEFAULT_DATE_FMT));

        if (OrderHelper.canShowOrderButton(data.getStatus())) {
            mBinding.btnStateDo.setVisibility(View.VISIBLE);
        } else {
            mBinding.btnStateDo.setVisibility(View.GONE);
        }

        mBinding.btnStateDo.setText(OrderHelper.getOrderBtnStateString(data.getStatus()));


        //收货人信息

//        mBinding.t
        if (!TextUtils.isEmpty(data.getReceiver())) {
            mBinding.linUserInfo.setVisibility(View.VISIBLE);
            mBinding.tvUserName.setText("收货人:" + data.getReceiver());
        }
        if (!TextUtils.isEmpty(data.getReMobile())) {
            mBinding.linUserInfo.setVisibility(View.VISIBLE);
            mBinding.tvPhone.setText(data.getReMobile());
        }
        if (!TextUtils.isEmpty(data.getReAddress())) {
            mBinding.tvAddress.setVisibility(View.VISIBLE);
            mBinding.tvAddress.setText(data.getReAddress());
        }

        //物流信息
        mBinding.tvLogisticscompany.setText(data.getLogisiticsCompany());
        mBinding.tvLogisticscode.setText(data.getLogisiticsCode());

        if (TextUtils.isEmpty(data.getLogisiticsCompany())) {
            mBinding.linLogisticscompany.setVisibility(View.GONE);
        } else {
            mBinding.linLogisticscompany.setVisibility(View.VISIBLE);
        }
        if (TextUtils.isEmpty(data.getLogisiticsCode())) {
            mBinding.linLogisticscode.setVisibility(View.GONE);
        } else {
            mBinding.linLogisticscode.setVisibility(View.VISIBLE);
        }

    }

}
