package com.guitrilha.busroutes.presenter.contract;

import com.google.android.gms.maps.model.LatLng;
import com.guitrilha.busroutes.presenter.Presenter;
import com.guitrilha.busroutes.view.contract.MapsView;

/**
 * Created by Guilherme on 12/10/2016.
 */

public interface MapsPresenter extends Presenter<MapsView> {

    void onSearchClicked(LatLng position);

    LatLng getLastPosition();

    void onMapClicked(LatLng latLng);
}
