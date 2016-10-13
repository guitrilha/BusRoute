package com.guitrilha.busroutes.view.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.guitrilha.busroutes.R;
import com.guitrilha.busroutes.presenter.contract.MapsPresenter;
import com.guitrilha.busroutes.view.AbstractActivity;
import com.guitrilha.busroutes.view.contract.MapsView;

import static com.guitrilha.busroutes.presenter.contract.RoutesPresenter.EXTRA_STREET_NAME;

public class MapsViewImpl extends AbstractActivity<MapsPresenter> implements MapsView, OnMapReadyCallback, GoogleMap.OnMapClickListener, View.OnClickListener {

    private final LatLng initialPosition = new LatLng(-27.5955782, -48.5368736);
    private final long initialZoom = 13;
    private GoogleMap mMap;
    private Marker mCurrentMarker;

    @Override
    protected int getLayoutToInflate() {
        return R.layout.activity_maps;
    }

    @Override
    protected Class<MapsPresenter> getPresenterClazz() {
        return MapsPresenter.class;
    }

    @Override
    protected void onLayoutInflated() {
        super.onLayoutInflated();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_search);
        fab.setOnClickListener(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.moveCamera(CameraUpdateFactory.newLatLng(initialPosition));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(initialZoom), 2000, null);
        mMap.setOnMapClickListener(this);
        LatLng lastPositionSelected = getPresenter().getLastPosition();
        if(lastPositionSelected!=null){
            showMarker(lastPositionSelected);
        }
    }

    @Override
    public void onMapClick(LatLng latLng) {
        getPresenter().onMapClicked(latLng);
        showMarker(latLng);
    }

    @Override
    public void showMarker(LatLng position) {
        clearMarker();
        mCurrentMarker = mMap.addMarker(new MarkerOptions()
                .position(position)
                .draggable(true));
        showSearchButton();
    }

    private void showSearchButton() {
        findViewById(R.id.fab_search).setVisibility(View.VISIBLE);
    }

    private void clearMarker() {
        if (mCurrentMarker != null) {
            mCurrentMarker.remove();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_search:
                getPresenter().onSearchClicked(mCurrentMarker.getPosition());
        }
    }

    @Override
    public void searchRoutesForStreet(String streetName) {
        Intent intent = new Intent(MapsViewImpl.this, RoutesViewImpl.class);
        intent.putExtra(EXTRA_STREET_NAME, streetName);
        startActivity(intent);
    }
}