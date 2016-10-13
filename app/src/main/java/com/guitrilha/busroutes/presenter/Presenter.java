package com.guitrilha.busroutes.presenter;

import android.content.Context;
import android.os.Bundle;

import com.guitrilha.busroutes.view.View;

/**
 * Created by Guilherme on 08/10/2016.
 */

public interface Presenter<T extends View> {

    void setApplicationContext(Context mApplicationContext);

    void setView(T view);

    void onCreate(Bundle extras, Bundle savedInstanceState);

    void onSaveInstanceState(Bundle outState);

    void onRestoreInstanceState(Bundle savedInstanceState);

    void onAfterStatedRestored();
}
