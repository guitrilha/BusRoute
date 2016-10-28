package com.guitrilha.busroutes.presenter.impl;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;

import com.guitrilha.busroutes.R;
import com.guitrilha.busroutes.model.client.APICallback;
import com.guitrilha.busroutes.model.contract.RouteModel;
import com.guitrilha.busroutes.model.entity.Route;
import com.guitrilha.busroutes.model.impl.RouteModelImpl;
import com.guitrilha.busroutes.presenter.AbstractPresenter;
import com.guitrilha.busroutes.presenter.contract.RoutesPresenter;
import com.guitrilha.busroutes.view.contract.RoutesView;

/**
 * Created by Guilherme on 08/10/2016.
 */

public class RoutesPresenterImpl extends AbstractPresenter<RoutesView> implements RoutesPresenter {

    private static final String STATE_QUERY = "query";

    RouteModel routeModel;
    private String mLastQuery;

    @Override
    public void onCreate(Bundle extras, Bundle savedInstanceState) {
        super.onCreate(extras, savedInstanceState);
        routeModel = new RouteModelImpl();
        if (extras.containsKey(EXTRA_STREET_NAME)) {
            onSearchRoute(extras.getString(EXTRA_STREET_NAME));
        }
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mLastQuery = savedInstanceState.getString(STATE_QUERY);
    }

    @Override
    public void onAfterStatedRestored() {
        super.onAfterStatedRestored();
        if (mLastQuery != null) {
            onSearchRoute(mLastQuery);
        }
    }

    @Override
    public void onSearchRoute(String query) {
        mLastQuery = query;
        Context context = getApplicationContext();
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            new FindRoutesByStopNameAsync().execute(query);
        } else {
            getView().showErrorMessage(getApplicationContext().getString(R.string.error_no_internet));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (mLastQuery != null) {
            outState.putString(STATE_QUERY, mLastQuery);
        }
    }

    private class FindRoutesByStopNameAsync extends AsyncTask<String, Void, APICallback<Route>> {

        private String query;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            getView().onSearchRoutesStarted();
        }

        @Override
        protected APICallback<Route> doInBackground(String... params) {
            query = params[0];
            return routeModel.findRoutesByStopName(query);
        }

        @Override
        protected void onPostExecute(APICallback<Route> callback) {
            super.onPostExecute(callback);
            getView().onSearchRoutesFinished();
            if (callback.exception == null) {
                if (callback.errorMsg == null) {
                    if (callback.itens != null) {
                        getView().showRoutes(callback.itens, query);
                    }
                } else {
                    getView().showErrorMessage(callback.errorMsg);
                }
            }else{
                getView().showUnexpectedError();
            }
        }
    }
}