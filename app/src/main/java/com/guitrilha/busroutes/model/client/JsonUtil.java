package com.guitrilha.busroutes.model.client;

import com.guitrilha.busroutes.model.entity.Departure;
import com.guitrilha.busroutes.model.entity.Route;
import com.guitrilha.busroutes.model.entity.Stop;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Guilherme on 08/10/2016.
 */

public class JsonUtil {

    private static final String STOP_NAME = "stopName";
    private static final String PARAMS = "params";

    public String getJsonToFindRoutesByStopName(String stopName) {
        JSONObject jsonObject = new JSONObject();
        try {
            JSONObject stopNameJsonObject = new JSONObject();
            stopNameJsonObject.put(STOP_NAME, stopName);
            jsonObject.put(PARAMS, stopNameJsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    private static final String ROWS = "rows";
    private static final String ID = "id";
    private static final String ROUTE_SHORT_NAME = "shortName";
    private static final String ROUTE_LONG_NAME = "longName";
    private static final String ROUTE_LAST_MODIFIED_DATE = "lastModifiedDate";
    private static final String ROUTE_AGENCY_ID = "agencyId";

    public List<Route> getRoutes(String jsonResult) {
        List<Route> routes = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(jsonResult);
            JSONArray jsonArray = jsonObject.getJSONArray(ROWS);
            if (jsonArray.length() > 0) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    Route route = new Route();
                    JSONObject routeJsonObject = jsonArray.getJSONObject(i);
                    route.id = routeJsonObject.getLong(ID);
                    route.shortName = routeJsonObject.getString(ROUTE_SHORT_NAME);
                    route.longName = routeJsonObject.getString(ROUTE_LONG_NAME);
                    String date = routeJsonObject.getString(ROUTE_LAST_MODIFIED_DATE);
                    route.lastModifiedDate = getDatefromString(date);
                    route.agencyId = routeJsonObject.getLong(ROUTE_AGENCY_ID);
                    routes.add(route);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return routes;
    }

    private static final String ROUTE_ID = "routeId";

    public String getJsonForRouteId(long routeId) {
        JSONObject jsonObject = new JSONObject();
        try {
            JSONObject routeNameJsonObject = new JSONObject();
            routeNameJsonObject.put(ROUTE_ID, routeId);
            jsonObject.put(PARAMS, routeNameJsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    private static final String NAME = "name";
    private static final String STOP_SEQUENCE = "sequence";
    private static final String STOP_ROUTE_ID = "route_id";

    public List<Stop> getStops(String jsonResult) {
        List<Stop> stops = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(jsonResult);
            JSONArray jsonArray = jsonObject.getJSONArray(ROWS);
            if (jsonArray.length() > 0) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    Stop stop = new Stop();
                    JSONObject stopJsonObject = jsonArray.getJSONObject(i);
                    stop.id = stopJsonObject.getLong(ID);
                    stop.name = stopJsonObject.getString(NAME);
                    stop.sequence = stopJsonObject.getInt(STOP_SEQUENCE);
                    stop.routeId = stopJsonObject.getLong(STOP_ROUTE_ID);
                    stops.add(stop);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return stops;
    }

    private static final String DEPARTURE_CALENDAR = "calendar";
    private static final String DEPARTURE_TIME = "time";

    public List<Departure> getDepartures(String jsonResult) {
        List<Departure> departures = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(jsonResult);
            JSONArray jsonArray = jsonObject.getJSONArray(ROWS);
            if (jsonArray.length() > 0) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    Departure departure = new Departure();
                    JSONObject stopJsonObject = jsonArray.getJSONObject(i);
                    departure.id = stopJsonObject.getLong(ID);
                    departure.calendar = stopJsonObject.getString(DEPARTURE_CALENDAR);
                    departure.time = stopJsonObject.getString(DEPARTURE_TIME);
                    departures.add(departure);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return departures;
    }

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    private Date getDatefromString(String dateString) {
        DateFormat df = new SimpleDateFormat(DATE_FORMAT);
        Date date;
        try {
            date = df.parse(dateString);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
