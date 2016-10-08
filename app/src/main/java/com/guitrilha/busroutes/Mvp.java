package com.guitrilha.busroutes;

import android.content.Context;

import com.guitrilha.busroutes.presenter.Presenters;

/**
 * Created by Guilherme on 08/10/2016.
 */

public class Mvp {

    private static Mvp instance;
    private Presenters mPresenters;

    public static Mvp getInstance(){
        if(instance==null)
            instance = new Mvp();
        return instance;
    }

    private Mvp(){
        mPresenters = new Presenters();
    }

    public Presenters getPresenters(){
        return mPresenters;
    }

    public void setApplicationContext(Context applicationContext){
        mPresenters.setApplicationContext(applicationContext);
    }
}
