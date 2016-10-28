package com.guitrilha.busroutes.presenter.impl;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.guitrilha.busroutes.R;
import com.guitrilha.busroutes.model.client.APICallback;
import com.guitrilha.busroutes.model.entity.CalendarType;
import com.guitrilha.busroutes.model.entity.Departure;
import com.guitrilha.busroutes.model.entity.Stop;
import com.guitrilha.busroutes.presenter.contract.TimetablePresenter;
import com.guitrilha.busroutes.view.contract.TimetableView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Guilherme on 09/10/2016.
 */

public class TimetablePresenterImpl extends BaseAbstractDetailRoutePresenter<TimetableView> implements TimetablePresenter {

    @Override
    public void onCreate(@NonNull Bundle extras, Bundle savedInstanceState) {
        super.onCreate(extras, savedInstanceState);
        findRouteTimetable();
    }

    private void findRouteTimetable() {
        Context context = getApplicationContext();
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            new FindRouteTimetable(this).execute(getRouteId());
        } else {
            getView().showErrorMessage(context.getString(R.string.error_no_internet));
        }
    }

    private class FindRouteTimetable extends AsyncTask<Long, Void, APICallback<Departure>> {

        TimetablePresenterImpl mPresenter;

        public FindRouteTimetable(TimetablePresenterImpl presenter) {
            mPresenter = presenter;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            getView().onSearchDeparturesStarted();
        }

        @Override
        protected APICallback<Departure> doInBackground(Long... params) {
            return routeModel.findDeparturesByRouteId(params[0]);
        }

        @Override
        protected void onPostExecute(APICallback<Departure> callback) {
            super.onPostExecute(callback);
            getView().onSearchDeparturesFinished();
            if (callback.exception == null) {
                if (callback.errorMsg == null) {
                    if (callback.itens != null) {
                        if (!callback.itens.isEmpty()) {
                            getView().showWeekdayDepartures(mPresenter.getWeekdayDeparture(callback.itens));
                            getView().showSaturdayDepartures(mPresenter.getSaturdayDeparture(callback.itens));
                            getView().showSundayDepartures(mPresenter.getSundayDeparture(callback.itens));
                        } else {
                            getView().showNoTimetableFound();
                        }
                    }
                } else {
                    getView().showErrorMessage(callback.errorMsg);
                }
            } else {
                getView().showUnexpectedError();
            }
        }
    }

    private List<Departure> getSundayDeparture(List<Departure> itens) {
        List<Departure> sundayDepartureList = new ArrayList<>();
        int pos = 0;
        if (itens.size() > 0)
            while (itens.get(pos).calendarType == CalendarType.SUNDAY) {
                sundayDepartureList.add(itens.get(pos));
                itens.remove(pos);
                if (itens.size() == 0)
                    return sundayDepartureList;
            }
        return sundayDepartureList;
    }

    private List<Departure> getSaturdayDeparture(List<Departure> itens) {
        List<Departure> saturdayDepartureList = new ArrayList<>();
        int pos = 0;
        if (itens.size() > 0)
            while (itens.get(pos).calendarType == CalendarType.SATURDAY) {
                saturdayDepartureList.add(itens.get(pos));
                itens.remove(pos);
                if (itens.size() == 0)
                    return saturdayDepartureList;
            }
        return saturdayDepartureList;
    }

    private List<Departure> getWeekdayDeparture(List<Departure> itens) {
        List<Departure> weekdayDepartureList = new ArrayList<>();
        int pos = 0;
        if (itens.size() > 0)
            while (itens.get(pos).calendarType == CalendarType.WEEKDAY) {
                weekdayDepartureList.add(itens.get(pos));
                itens.remove(pos);
                if (itens.size() == 0)
                    return weekdayDepartureList;
            }
        return weekdayDepartureList;
    }

}
