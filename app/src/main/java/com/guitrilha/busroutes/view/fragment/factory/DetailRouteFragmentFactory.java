package com.guitrilha.busroutes.view.fragment.factory;

import android.content.Context;

import com.guitrilha.busroutes.R;
import com.guitrilha.busroutes.presenter.Presenter;
import com.guitrilha.busroutes.view.View;
import com.guitrilha.busroutes.view.fragment.BaseDetailRouteViewImpl;
import com.guitrilha.busroutes.view.fragment.ItineraryViewImpl;
import com.guitrilha.busroutes.view.fragment.TimetableViewImpl;

/**
 * Created by Guilherme on 09/10/2016.
 */

public class DetailRouteFragmentFactory {

    public static final int FRAGMENTS_COUNT = 2;

    public static final int STREETS = 0;
    public static final int TIMETABLE = 1;

    public static BaseDetailRouteViewImpl<? extends Presenter<? extends View<?>>> createFragment(int position) {
        switch (position) {
            case STREETS:
                return new ItineraryViewImpl();
            case TIMETABLE:
                return new TimetableViewImpl();
            default:
                throw new IllegalArgumentException("Invalid position to BaseDetailRouteFragment");
        }
    }

    public static CharSequence createTitle(int position, Context context) {
        int stringResource = getStringResource(position);
        return context.getString(stringResource);
    }

    private static int getStringResource(int position) {
        switch (position) {
            case STREETS:
                return R.string.title_fragment_itinerary;
            case TIMETABLE:
                return R.string.title_fragment_timetable;
            default:
                throw new IllegalArgumentException("Invalid position to BaseDetailRouteFragment");
        }
    }

}
