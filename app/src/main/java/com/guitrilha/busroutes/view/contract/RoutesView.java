package com.guitrilha.busroutes.view.contract;

import com.guitrilha.busroutes.model.entity.Route;
import com.guitrilha.busroutes.presenter.contract.RoutesPresenter;
import com.guitrilha.busroutes.view.View;

import java.util.List;

/**
 * Created by Guilherme on 08/10/2016.
 */

public interface RoutesView extends View<RoutesPresenter> {

    void showRoutes(List<Route> routes, String query);

    void onSearchRoutesStarted();

    void onSearchRoutesFinished();

    void showUnexpectedError();

    void showErrorMessage(String errorMsg);
}
