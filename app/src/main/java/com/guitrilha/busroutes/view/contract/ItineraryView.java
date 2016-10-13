package com.guitrilha.busroutes.view.contract;

import com.guitrilha.busroutes.model.entity.Stop;
import com.guitrilha.busroutes.presenter.contract.ItineraryPresenter;
import com.guitrilha.busroutes.view.View;

import java.util.List;

/**
 * Created by Guilherme on 09/10/2016.
 */

public interface ItineraryView extends View<ItineraryPresenter> {

    void onSearchStopsStarted();

    void onSearchRoutesFinished();

    void showStops(List<Stop> stops);

    void showUnexpectedError();

    void showErrorMessage(String errorMsg);
}
