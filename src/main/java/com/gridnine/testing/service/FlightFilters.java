package com.gridnine.testing.service;

import com.gridnine.testing.entity.Flight;

import java.util.List;

public interface FlightFilters {
    List<Flight> build();
    FlightFilters departureTimeBeforeNowFilter();
    FlightFilters arrivalEarlierThenDepartureFilter();
    FlightFilters landingTimeMoreThenTwoHoursFilter();

}
