package com.guitrilha.busroutes.view.contract;

import com.google.android.gms.maps.model.LatLng;
import com.guitrilha.busroutes.presenter.contract.MapsPresenter;
import com.guitrilha.busroutes.view.View;

/**
 * Created by Guilherme on 12/10/2016.
 */

public interface MapsView extends View<MapsPresenter> {

    void showMarker(LatLng position);

    void searchRoutesForStreet(String streetName);
}
