package com.guitrilha.busroutes.view.fragment;

import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.guitrilha.busroutes.R;
import com.guitrilha.busroutes.model.entity.Departure;
import com.guitrilha.busroutes.presenter.contract.TimetablePresenter;
import com.guitrilha.busroutes.view.adapter.DepartureListAdapter;
import com.guitrilha.busroutes.view.contract.TimetableView;

import java.util.List;

/**
 * Created by Guilherme on 09/10/2016.
 */

public class TimetableViewImpl extends BaseDetailRouteViewImpl<TimetablePresenter> implements TimetableView {

    private DepartureListAdapter mWeekdayAdapter;
    private DepartureListAdapter mSaturdayAdapter;
    private DepartureListAdapter mSundayAdapter;

    @Override
    protected int getLayoutToInflate() {
        return R.layout.fragment_timetable;
    }

    @Override
    protected Class<TimetablePresenter> getPresenterClazz() {
        return TimetablePresenter.class;
    }

    @Override
    protected void onLayoutInflated() {
        super.onLayoutInflated();

        mWeekdayAdapter = new DepartureListAdapter();
        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.weekday_list);
        recyclerView.setAdapter(mWeekdayAdapter);
        recyclerView.setNestedScrollingEnabled(false);

        mSaturdayAdapter = new DepartureListAdapter();
        recyclerView = (RecyclerView) getActivity().findViewById(R.id.saturday_list);
        recyclerView.setAdapter(mSaturdayAdapter);
        recyclerView.setNestedScrollingEnabled(false);

        mSundayAdapter = new DepartureListAdapter();
        recyclerView = (RecyclerView) getActivity().findViewById(R.id.sunday_list);
        recyclerView.setAdapter(mSundayAdapter);
        recyclerView.setNestedScrollingEnabled(false);
    }

    @Override
    public void onSearchDeparturesStarted() {
        setVisibleOrGone(R.id.layout_timetables, false);
        setVisibleOrGone(R.id.textview_error_timetable, false);
        setVisibleOrGone(R.id.progressBar_timetable, true);
    }

    @Override
    public void onSearchDeparturesFinished() {
        setVisibleOrGone(R.id.progressBar_timetable, false);
    }


    @Override
    public void showWeekdayDepartures(List<Departure> departures) {
        setVisibleOrGone(R.id.layout_timetables, true);
        if (!departures.isEmpty()) {
            setVisibleOrGone(R.id.weekday_list, true);
            mWeekdayAdapter.setDepartureList(departures);
        } else {
            setVisibleOrGone(R.id.textview_no_departure_weekday, true);
        }
    }

    @Override
    public void showSaturdayDepartures(List<Departure> departures) {
        if (!departures.isEmpty()) {
            setVisibleOrGone(R.id.saturday_list, true);
            mSaturdayAdapter.setDepartureList(departures);
        } else {
            setVisibleOrGone(R.id.textview_no_departure_saturday, true);
        }
    }

    @Override
    public void showSundayDepartures(List<Departure> departures) {
        if (!departures.isEmpty()) {
            setVisibleOrGone(R.id.sunday_list, true);
            mSundayAdapter.setDepartureList(departures);
        } else {
            setVisibleOrGone(R.id.textview_no_departure_sunday, true);
        }
    }

    @Override
    public void showUnexpectedError() {
        showErrorMessage(getString(R.string.error_unexpected));
    }

    @Override
    public void showErrorMessage(String errorMsg) {
        setVisibleOrGone(R.id.textview_error_timetable, true);
        ((TextView) getActivity().findViewById(R.id.textview_error_timetable)).setText(errorMsg);
    }

    @Override
    public void showNoTimetableFound() {
        setVisibleOrGone(R.id.textview_error_timetable, true);
        ((TextView) getActivity().findViewById(R.id.textview_error_timetable)).setText(R.string.error_no_timetable);
    }
}
