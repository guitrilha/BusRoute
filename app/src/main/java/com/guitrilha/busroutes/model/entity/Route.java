package com.guitrilha.busroutes.model.entity;

import java.util.Date;
import java.util.List;

/**
 * Created by Guilherme on 08/10/2016.
 */

public class Route {

    public long id;

    public String shortName;

    public String longName;

    public Date lastModifiedDate;

    public long agencyId;

    public List<Stop> stops;

    public List<Departure> departures;
}
