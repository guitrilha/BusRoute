package com.guitrilha.busroutes.presenter.impl;

import android.os.Bundle;

import com.guitrilha.busroutes.model.entity.Route;
import com.guitrilha.busroutes.presenter.AbstractPresenter;
import com.guitrilha.busroutes.presenter.contract.DetailRoutePresenter;
import com.guitrilha.busroutes.view.contract.DetailRouteView;

/**
 * Created by Guilherme on 09/10/2016.
 */

public class DetailRoutePresenterImpl extends AbstractPresenter<DetailRouteView> implements DetailRoutePresenter  {

    private long mRouteId;
    private String mRouteName;

    @Override
    public void onCreate(Bundle extras, Bundle savedInstanceState) {
        super.onCreate(extras, savedInstanceState);
        if(extras.containsKey(EXTRA_ROUTE_ID)){
            mRouteId = extras.getLong(EXTRA_ROUTE_ID);
        }
        if(extras.containsKey(EXTRA_ROUTE_NAME)){
            mRouteName = extras.getString(EXTRA_ROUTE_NAME);
        }
        getView().showRouteName(mRouteName);
    }

    @Override
    public long getRouteId() {
        return mRouteId;
    }
}
