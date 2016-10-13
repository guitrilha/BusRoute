package com.guitrilha.busroutes.model.entity;

/**
 * Created by Guilherme on 08/10/2016.
 */

public class Departure {

    public long id;

    public String calendar;

    @CalendarType
    public int calendarType;

    public String time;
}
