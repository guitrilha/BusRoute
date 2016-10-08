package com.guitrilha.busroutes;

import android.app.Application;

import com.guitrilha.busroutes.presenter.Presenters;
import com.guitrilha.busroutes.presenter.contract.RoutesPresenter;
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
    }
}
