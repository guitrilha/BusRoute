package com.guitrilha.busroutes.model.entity;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Guilherme on 08/10/2016.
 */

@Retention(RetentionPolicy.SOURCE)
@IntDef({
        CalendarType.UNDEFINED,
        CalendarType.WEEKDAY,
        CalendarType.SATURDAY,
        CalendarType.SUNDAY,
})
public @interface CalendarType {

    int UNDEFINED = -1;
    int WEEKDAY = 0;
    int SATURDAY = 1;
    int SUNDAY = 2;

}