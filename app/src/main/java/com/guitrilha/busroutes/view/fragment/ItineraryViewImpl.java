package com.guitrilha.busroutes.view.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.guitrilha.busroutes.R;
import com.guitrilha.busroutes.model.entity.Stop;
import com.guitrilha.busroutes.presenter.contract.ItineraryPresenter;
import com.guitrilha.busroutes.view.adapter.StopListAdapter;
import com.guitrilha.busroutes.view.contract.ItineraryView;

import java.util.List;

/**
 * Created by Guilherme on 09/10/2016.
 */

public class ItineraryViewImpl extends BaseDetailRouteViewImpl<ItineraryPresenter> implements ItineraryView {

    private StopListAdapter mStopsListAdapter;

    @Override
    protected int getLayoutToInflate() {
        return R.layout.fragment_itinerary;
    }

    @Override
    protected Class<ItineraryPresenter> getPresenterClazz() {
        return ItineraryPresenter.class;
    }

    @Override
    protected void onLayoutInflated() {
        super.onLayoutInflated();
        mStopsListAdapter = new StopListAdapter(getActivity());
        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.stops_list);
        recyclerView.setAdapter(mStopsListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onSearchStopsStarted() {
        setVisibleOrGone(R.id.progressBar_itinerary, true);
        setVisibleOrGone(R.id.stops_list, false);
        setVisibleOrGone(R.id.textview_error_itinerary, false);
    }

    @Override
    public void onSearchRoutesFinished() {
        setVisibleOrGone(R.id.progressBar_itinerary, false);
    }

    @Override
    public void showStops(List<Stop> stops) {
        if (!stops.isEmpty()) {
            setVisibleOrGone(R.id.stops_list, true);
            mStopsListAdapter.setStopList(stops);
        } else {
            showErrorMessage(getString(R.string.error_no_stops_found));
        }
    }

    @Override
    public void showUnexpectedError() {
        showErrorMessage(getString(R.string.error_unexpected));
    }

    @Override
    public void showErrorMessage(String errorMsg) {
        setVisibleOrGone(R.id.textview_error, true);
        ((TextView) getActivity().findViewById(R.id.textview_error_itinerary)).setText(errorMsg);
    }
}
