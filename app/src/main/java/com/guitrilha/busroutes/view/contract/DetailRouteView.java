package com.guitrilha.busroutes.view.contract;

import com.guitrilha.busroutes.model.entity.Route;
import com.guitrilha.busroutes.presenter.contract.DetailRoutePresenter;
import com.guitrilha.busroutes.view.View;

/**
 * Created by Guilherme on 09/10/2016.
 */

public interface DetailRouteView extends View<DetailRoutePresenter> {

    void showRouteName(String mRouteName);
}
