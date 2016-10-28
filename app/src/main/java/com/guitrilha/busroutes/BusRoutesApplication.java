package com.guitrilha.busroutes;

import android.app.Application;

import com.guitrilha.busroutes.presenter.Presenters;
import com.guitrilha.busroutes.presenter.contract.DetailRoutePresenter;
import com.guitrilha.busroutes.presenter.contract.ItineraryPresenter;
import com.guitrilha.busroutes.presenter.contract.MapsPresenter;
import com.guitrilha.busroutes.presenter.contract.TimetablePresenter;
import com.guitrilha.busroutes.presenter.contract.RoutesPresenter;
import com.guitrilha.busroutes.presenter.impl.DetailRoutePresenterImpl;
import com.guitrilha.busroutes.presenter.impl.ItineraryPresenterImpl;
import com.guitrilha.busroutes.presenter.impl.MapsPresenterImpl;
import com.guitrilha.busroutes.presenter.impl.TimetablePresenterImpl;
import com.guitrilha.busroutes.presenter.impl.RoutesPresenterImpl;

/**
 * Created by Guilherme on 08/10/2016.
 */

public class BusRoutesApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Mvp mvp = Mvp.getInstance();
        mvp.setApplicationContext(getApplicationContext());
        configPresenters(mvp.getPresenters());
    }

    public void configPresenters(Presenters presenters) {
        presenters.put(RoutesPresenter.class, RoutesPresenterImpl.class);
        presenters.put(DetailRoutePresenter.class, DetailRoutePresenterImpl.class);
        presenters.put(ItineraryPresenter.class, ItineraryPresenterImpl.class);
        presenters.put(TimetablePresenter.class, TimetablePresenterImpl.class);
        presenters.put(MapsPresenter.class, MapsPresenterImpl.class);
    }
}
