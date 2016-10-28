package com.guitrilha.busroutes.model.contract;

import com.guitrilha.busroutes.model.client.APICallback;
import com.guitrilha.busroutes.model.entity.Departure;
import com.guitrilha.busroutes.model.entity.Route;
import com.guitrilha.busroutes.model.entity.Stop;

import java.util.List;

/**
 * Created by Guilherme on 08/10/2016.
 */

public interface RouteModel {

    public APICallback<Route> findRoutesByStopName(String stopName);

    public APICallback<Stop> findStopsByRouteId(long routeId);

    public APICallback<Departure> findDeparturesByRouteId(long routeId);

}
