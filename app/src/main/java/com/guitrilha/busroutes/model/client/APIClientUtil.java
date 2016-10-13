package com.guitrilha.busroutes.model.client;

import android.util.Log;

import com.guitrilha.busroutes.model.entity.Departure;
import com.guitrilha.busroutes.model.entity.Route;
import com.guitrilha.busroutes.model.entity.Stop;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by Guilherme on 08/10/2016.
 */

public class APIClientUtil {

    private static final String UNICODE_FORMAT = "UTF-8";
    private static final String API_USERNAME = "WKD4N7YMA1uiM8V";
    private static final String API_PASSWORD = "DtdTtzMLQlA0hk2C1Yi5pLyVIlAQ68";

    private static final String HEADER_PARAM_ENVIRONMENT = "X-AppGlu-Environment";
    private static final String HEADER_PARAM_ENVIRONMENT_VALUE = "staging";
    private static final String HEADER_PARAM_CONTENT_TYPE = "Content-Type";
    private static final String HEADER_PARAM_CONTENT_TYPE_VALUE = "application/json";


    private static final String API_BASE_PATH = "https://api.appglu.com/v1/queries";
    private static final String FIND_ROUTES_BY_STOP_NAME_PATH = "/findRoutesByStopName/run";
    private static final String FIND_STOPS_BY_ROUTE_ID_PATH = "/findStopsByRouteId/run";
    private static final String FIND_DEPARTURES_BY_ROUTE_ID_PATH = "/findDeparturesByRouteId/run";

    private static final int RESULT_OK = 200;

    private static APIClientUtil instance;
    private JsonUtil mJsonUtil = new JsonUtil();

    public static APIClientUtil getInstance() {
        if (instance == null)
            instance = new APIClientUtil();
        return instance;
    }

    private APIClientUtil() {
        mJsonUtil = new JsonUtil();
        Authenticator.setDefault(new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(API_USERNAME, API_PASSWORD.toCharArray());
            }
        });
    }

    public APICallback<Route> findRoutesByStopName(String stopName) {
        APICallback<Route> callback = new APICallback<>();

        String bodyRequest = mJsonUtil.getJsonToFindRoutesByStopName(formatQueryToRequest(stopName));
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(API_BASE_PATH + FIND_ROUTES_BY_STOP_NAME_PATH);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.addRequestProperty(HEADER_PARAM_ENVIRONMENT, HEADER_PARAM_ENVIRONMENT_VALUE);
            urlConnection.addRequestProperty(HEADER_PARAM_CONTENT_TYPE, HEADER_PARAM_CONTENT_TYPE_VALUE);

            OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());
            out.write(bodyRequest.getBytes(Charset.forName(UNICODE_FORMAT)));
            out.close();

            int status = urlConnection.getResponseCode();
            if (status == RESULT_OK) {
                InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
                String jsonResult = readStream(inputStream);
                callback.itens = mJsonUtil.getRoutes(jsonResult);
                inputStream.close();
            } else {
                InputStream inputStream = new BufferedInputStream(urlConnection.getErrorStream());
                callback.errorMsg = readStream(inputStream);
                inputStream.close();
            }
            return callback;
        } catch (IOException e) {
            e.printStackTrace();
            callback.exception = e;
        } finally {
            urlConnection.disconnect();
        }
        return callback;
    }

    private String formatQueryToRequest(String query) {
        return "%".concat(query.trim().concat("%"));
    }

    public APICallback<Stop> findStopsByRouteId(long routeId) {
        APICallback<Stop> callback = new APICallback<>();
        String bodyRequest = mJsonUtil.getJsonForRouteId(routeId);
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(API_BASE_PATH + FIND_STOPS_BY_ROUTE_ID_PATH);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.addRequestProperty(HEADER_PARAM_ENVIRONMENT, HEADER_PARAM_ENVIRONMENT_VALUE);
            urlConnection.addRequestProperty(HEADER_PARAM_CONTENT_TYPE, HEADER_PARAM_CONTENT_TYPE_VALUE);

            OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());
            out.write(bodyRequest.getBytes(Charset.forName(UNICODE_FORMAT)));
            out.close();

            int status = urlConnection.getResponseCode();
            if (status == RESULT_OK) {
                InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
                String jsonResult = readStream(inputStream);
                callback.itens = mJsonUtil.getStops(jsonResult);
                inputStream.close();
            } else {
                InputStream inputStream = new BufferedInputStream(urlConnection.getErrorStream());
                callback.errorMsg = readStream(inputStream);
                inputStream.close();
            }
            return callback;
        } catch (IOException e) {
            e.printStackTrace();
            callback.exception = e;
        } finally {
            urlConnection.disconnect();
        }
        return callback;
    }

    public APICallback<Departure> findDeparturesByRouteId(long routeId) {
        APICallback<Departure> callback = new APICallback<>();
        String bodyRequest = mJsonUtil.getJsonForRouteId(routeId);
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(API_BASE_PATH + FIND_DEPARTURES_BY_ROUTE_ID_PATH);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.addRequestProperty(HEADER_PARAM_ENVIRONMENT, HEADER_PARAM_ENVIRONMENT_VALUE);
            urlConnection.addRequestProperty(HEADER_PARAM_CONTENT_TYPE, HEADER_PARAM_CONTENT_TYPE_VALUE);

            OutputStream outputStream = new BufferedOutputStream(urlConnection.getOutputStream());
            outputStream.write(bodyRequest.getBytes(Charset.forName("UTF-8")));
            outputStream.close();

            int status = urlConnection.getResponseCode();
            if (status == RESULT_OK) {
                InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
                String jsonResult = readStream(inputStream);
                callback.itens = mJsonUtil.getDepartures(jsonResult);
                inputStream.close();
            } else {
                InputStream inputStream = new BufferedInputStream(urlConnection.getErrorStream());
                callback.errorMsg = readStream(inputStream);
                inputStream.close();
            }
            return callback;
        } catch (IOException e) {
            e.printStackTrace();
            callback.exception = e;
        } finally {
            urlConnection.disconnect();
        }
        return callback;
    }

    private String readStream(InputStream inputStream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder result = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String resultString = result.toString();
        return resultString;
    }
}
