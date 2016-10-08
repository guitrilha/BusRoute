package com.guitrilha.busroutes.presenter;

import android.content.Context;
import android.os.Bundle;

import com.guitrilha.busroutes.view.View;
import java.lang.ref.WeakReference;

/**
 * Created by Guilherme on 08/10/2016.
 */

public abstract class AbstractPresenter<T extends View> implements Presenter<T> {

    private WeakReference<T> mView;
    private Context mContext;

    @Override
    public void onCreate(Bundle extras, Bundle savedInstanceState) {

    }

    @Override
    public void setApplicationContext(Context mApplicationContext) {
        mContext = mApplicationContext;
    }

    public Context getApplicationContext() {
        return mContext;
    }

    @Override
    public void setView(T view) {
        mView = new WeakReference<T>(view);
    }

    public T getView() {
        if (mView != null)
            return mView.get();
        return null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {

    }

}
