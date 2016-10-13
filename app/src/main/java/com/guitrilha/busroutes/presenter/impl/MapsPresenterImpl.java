package com.guitrilha.busroutes.presenter.impl;

import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.gms.maps.model.LatLng;
import com.guitrilha.busroutes.model.client.APICallback;
import com.guitrilha.busroutes.model.entity.Route;
import com.guitrilha.busroutes.presenter.AbstractPresenter;
import com.guitrilha.busroutes.presenter.contract.MapsPresenter;
import com.guitrilha.busroutes.view.contract.MapsView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Guilherme on 12/10/2016.
 */

public class MapsPresenterImpl extends AbstractPresenter<MapsView> implements MapsPresenter {

    private static final String STATE_CURRENT_MARKER = "current_marker";
    public LatLng mCurrentPosition;

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mCurrentPosition = savedInstanceState.getParcelable(STATE_CURRENT_MARKER);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mCurrentPosition != null) {
            outState.putParcelable(STATE_CURRENT_MARKER, mCurrentPosition);
        }

    }

    @Override
    public void onSearchClicked(LatLng position) {
        new FindStreetLocation().execute(position);
    }

    @Override
    public LatLng getLastPosition() {
        return mCurrentPosition;
    }

    @Override
    public void onMapClicked(LatLng latLng) {
        mCurrentPosition = latLng;
    }

    private class FindStreetLocation extends AsyncTask<LatLng, Void, String> {

        @Override
        protected String doInBackground(LatLng... params) {
            LatLng position = params[0];
            Geocoder geocoder;
            List<Address> addresses = new ArrayList<>();
            geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
            try {
                addresses = geocoder.getFromLocation(position.latitude, position.longitude, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String address = addresses.get(0).getAddressLine(0);
            return getStreetName(address);
        }

        private String getStreetName(String address) {
            String[] parts;
            if (address.contains(",")) {
                parts = address.split(",");
                return parts[0];
            } else if (address.contains("-")) {
                parts = address.split("-");
                return parts[0];
            }
            return address;
        }

        @Override
        protected void onPostExecute(String streetName) {
            super.onPostExecute(streetName);
            getView().searchRoutesForStreet(streetName);
        }
    }
}
