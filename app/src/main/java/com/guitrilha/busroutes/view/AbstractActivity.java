package com.guitrilha.busroutes.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.guitrilha.busroutes.Mvp;
import com.guitrilha.busroutes.presenter.Presenter;
import com.guitrilha.busroutes.presenter.Presenters;

/**
 * Created by Guilherme on 08/10/2016.
 */

public abstract class AbstractActivity<T extends Presenter> extends AppCompatActivity implements View<T> {

    private T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        injectPresenter();
        super.onCreate(savedInstanceState);
        inflateLayout();
        callPresenterOnCreate(savedInstanceState);
    }

    private void callPresenterOnCreate(Bundle savedInstanceState) {
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            extras = new Bundle();
        }
        mPresenter.onCreate(extras, savedInstanceState);
    }

    private void inflateLayout() {
        int layoutToInflate = getLayoutToInflate();
        setContentView(layoutToInflate);
        onLayoutInflate();
    }

    protected abstract int getLayoutToInflate();

    protected void onLayoutInflate() {

    }

    private void injectPresenter() {
        Class<T> presenterClazz = getPresenterClazz();
        Mvp mvp = Mvp.getInstance();
        Presenters presenters = mvp.getPresenters();
        T presenter = presenters.getPresenter(presenterClazz);
        setPresenter(presenter);
        presenter.setView(this);

    }

    protected abstract Class<T> getPresenterClazz();

    private void setPresenter(T presenter) {
        mPresenter = presenter;
    }

    public T getPresenter(){
        return mPresenter;
    }
}
