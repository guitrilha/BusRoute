package com.guitrilha.busroutes.presenter.impl;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.guitrilha.busroutes.R;
import com.guitrilha.busroutes.model.client.APICallback;
import com.guitrilha.busroutes.model.entity.Stop;
import com.guitrilha.busroutes.presenter.contract.ItineraryPresenter;
import com.guitrilha.busroutes.view.contract.ItineraryView;

/**
 * Created by Guilherme on 09/10/2016.
 */

public class ItineraryPresenterImpl extends BaseAbstractDetailRoutePresenter<ItineraryView> implements ItineraryPresenter {

    @Override
    public void onCreate(@NonNull Bundle extras, Bundle savedInstanceState) {
        super.onCreate(extras, savedInstanceState);
        findRouteItinerary();
    }

    private void findRouteItinerary() {
        Context context = getApplicationContext();
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            new FindRouteItinerary().execute(getRouteId());
        } else {
            getView().showErrorMessage(context.getString(R.string.error_no_internet));
        }
    }

    private class FindRouteItinerary extends AsyncTask<Long, Void, APICallback<Stop>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            getView().onSearchStopsStarted();
        }

        @Override
        protected APICallback<Stop> doInBackground(Long... params) {
            return routeModel.findStopsByRouteId(params[0]);
        }

        @Override
        protected void onPostExecute(APICallback<Stop> callback) {
            super.onPostExecute(callback);
            getView().onSearchRoutesFinished();
            if (callback.exception == null) {
                if (callback.errorMsg == null) {
                    if (callback.itens != null) {
                        getView().showStops(callback.itens);
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
