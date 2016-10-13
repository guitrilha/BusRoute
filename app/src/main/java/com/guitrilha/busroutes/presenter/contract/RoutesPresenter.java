package com.guitrilha.busroutes.presenter.contract;

import com.guitrilha.busroutes.presenter.Presenter;
import com.guitrilha.busroutes.view.contract.RoutesView;

/**
 * Created by Guilherme on 08/10/2016.
 */

public interface RoutesPresenter extends Presenter<RoutesView> {

    public static final String EXTRA_STREET_NAME = "street_name";

    void onSearchRoute(String query);
}
