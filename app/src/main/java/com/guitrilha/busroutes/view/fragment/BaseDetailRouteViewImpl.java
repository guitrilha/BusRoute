package com.guitrilha.busroutes.view.fragment;

import android.content.Context;

import com.guitrilha.busroutes.presenter.contract.BaseDetailRoutePresenter;
import com.guitrilha.busroutes.view.AbstractFragment;
import com.guitrilha.busroutes.view.activity.DetailRouteViewImpl;

/**
 * Created by Guilherme on 09/10/2016.
 */

public abstract class BaseDetailRouteViewImpl<T extends BaseDetailRoutePresenter> extends AbstractFragment<T> {


    private DetailRouteViewImpl mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (DetailRouteViewImpl) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mActivity = null;
    }

    protected DetailRouteViewImpl getDetailRouteActivity() {
        return mActivity;
    }

}
