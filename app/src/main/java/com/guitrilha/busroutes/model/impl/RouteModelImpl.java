package com.guitrilha.busroutes.model.impl;

import com.guitrilha.busroutes.model.client.APICallback;
import com.guitrilha.busroutes.model.client.APIClientUtil;
import com.guitrilha.busroutes.model.contract.RouteModel;
import com.guitrilha.busroutes.model.entity.CalendarType;
import com.guitrilha.busroutes.model.entity.Departure;
import com.guitrilha.busroutes.model.entity.Route;
import com.guitrilha.busroutes.model.entity.Stop;

/**
 * Created by Guilherme on 08/10/2016.
 */

public class RouteModelImpl implements RouteModel{

    @Override
    public APICallback<Route> findRoutesByStopName(String stopName) {
        APIClientUtil apiClient = APIClientUtil.getInstance();
        return apiClient.findRoutesByStopName(stopName);
    }

    @Override
    public APICallback<Departure> findDeparturesByRouteId(long routeId) {
        APIClientUtil apiClient = APIClientUtil.getInstance();
        APICallback<Departure> callback = apiClient.findDeparturesByRouteId(routeId);
        if (callback.itens != null && !callback.itens.isEmpty()) {
            for (Departure departure : callback.itens) {
                defineCalendarType(departure);
            }
        }
        return callback;
    }
    private static final String WEEKDAY_STR = "WEEKDAY";
    private static final String SATURDAY_STR = "SATURDAY";
    private static final String SUNDAY_STR = "SUNDAY";

    private void defineCalendarType(Departure departure) {
        if (departure.calendar.equals(WEEKDAY_STR)) {
            departure.calendarType = CalendarType.WEEKDAY;
        } else if (departure.calendar.equals(SATURDAY_STR)) {
            departure.calendarType = CalendarType.SATURDAY;
        } else if (departure.calendar.equals(SUNDAY_STR)) {
            departure.calendarType = CalendarType.SUNDAY;
        } else {
            departure.calendarType = CalendarType.UNDEFINED;
        }
    }

    @Override
    public APICallback<Stop> findStopsByRouteId(long routeId) {
        APIClientUtil apiClient = APIClientUtil.getInstance();
        return apiClient.findStopsByRouteId(routeId);
    }

}
