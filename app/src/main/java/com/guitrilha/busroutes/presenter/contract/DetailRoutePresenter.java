package com.guitrilha.busroutes.presenter.contract;

import com.guitrilha.busroutes.model.entity.Route;
import com.guitrilha.busroutes.presenter.Presenter;
import com.guitrilha.busroutes.view.contract.DetailRouteView;

/**
 * Created by Guilherme on 09/10/2016.
 */

public interface DetailRoutePresenter extends Presenter<DetailRouteView> {

    public static final String EXTRA_ROUTE_ID = DetailRoutePresenter.class.getName() + ".routeId";
    public static final String EXTRA_ROUTE_NAME = DetailRoutePresenter.class.getName() + ".longName";

    long getRouteId();
}
