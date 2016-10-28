package com.guitrilha.busroutes.presenter.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.guitrilha.busroutes.model.contract.RouteModel;
import com.guitrilha.busroutes.model.entity.Route;
import com.guitrilha.busroutes.model.impl.RouteModelImpl;
import com.guitrilha.busroutes.presenter.AbstractPresenter;
import com.guitrilha.busroutes.presenter.Presenter;
import com.guitrilha.busroutes.view.View;

import static com.guitrilha.busroutes.presenter.contract.DetailRoutePresenter.EXTRA_ROUTE_ID;

/**
 * Created by Guilherme on 09/10/2016.
 */

public abstract class BaseAbstractDetailRoutePresenter<T extends View> extends AbstractPresenter<T> implements Presenter<T> {

    RouteModel routeModel;
    private long mRouteId;

    @Override
    public void onCreate(@NonNull Bundle extras, Bundle savedInstanceState) {
        super.onCreate(extras, savedInstanceState);
        routeModel = new RouteModelImpl();
        if (extras.containsKey(EXTRA_ROUTE_ID))
            setRouteId(extras.getLong(EXTRA_ROUTE_ID));
    }

    public long getRouteId() {
        return mRouteId;
    }

    private void setRouteId(long routeId) {
        mRouteId = routeId;
    }

}