package com.guitrilha.busroutes.view.contract;

import com.guitrilha.busroutes.model.entity.Departure;
import com.guitrilha.busroutes.presenter.contract.TimetablePresenter;
import com.guitrilha.busroutes.view.View;

import java.util.List;

/**
 * Created by Guilherme on 09/10/2016.
 */

public interface TimetableView extends View<TimetablePresenter> {

    void onSearchDeparturesStarted();

    void onSearchDeparturesFinished();

    void showWeekdayDepartures(List<Departure> departures);

    void showSaturdayDepartures(List<Departure> departures);

    void showSundayDepartures(List<Departure> departures);

    void showUnexpectedError();

    void showErrorMessage(String errorMsg);

    void showNoTimetableFound();
}
