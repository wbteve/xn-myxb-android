package com.cdkj.myxb.module.maintab;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cdkj.baselibrary.base.BaseLazyFragment;
import com.cdkj.myxb.R;
import com.cdkj.myxb.databinding.FragmentAdviceBinding;

/**
 * Created by cdkj on 2018/2/8.
 */

public class AdviceFragment extends BaseLazyFragment {


    private FragmentAdviceBinding mBinding;

    public static AdviceFragment getInstanse() {
        AdviceFragment fragment = new AdviceFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_advice, null, false);

        mBinding.headerLayout.tvShowAll.setOnClickListener(view -> {

        });

        return mBinding.getRoot();
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected void onInvisible() {

    }
}
