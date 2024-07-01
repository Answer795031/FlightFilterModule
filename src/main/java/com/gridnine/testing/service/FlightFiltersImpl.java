package com.gridnine.testing.service;

import com.gridnine.testing.entity.Flight;
import com.gridnine.testing.entity.Segment;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.gridnine.testing.constants.Constants.CURRENT_DATE;

public class FlightFiltersImpl implements FlightFilters {
    private List<Flight> flights;

    public FlightFiltersImpl(List<Flight> flights) {
        this.flights = new ArrayList<>(flights);
    }

    @Override
    public List<Flight> build() {
        return flights;
    }

    // Вылет до текущего момента времени
    @Override
    public FlightFilters departureTimeBeforeNowFilter() {
        flights.removeIf(flight ->
                flight
                        .getSegments()
                        .stream()
                        .anyMatch(segment -> segment
                                .getDepartureDate()
                                .isBefore(CURRENT_DATE))
        );
        return this;
    }

    // Сегменты с датой прилёта раньше даты вылета
    @Override
    public FlightFilters arrivalEarlierThenDepartureFilter() {
        flights.removeIf(flight ->
                flight
                        .getSegments()
                        .stream()
                        .anyMatch(segment -> segment
                                .getArrivalDate()
                                .isBefore(segment.getDepartureDate()))
        );
        return this;
    }

    // Перелеты, где общее время, проведённое на земле, превышает два часа
    @Override
    public FlightFilters landingTimeMoreThenTwoHoursFilter() {
        flights.removeIf(flight -> {
            List<Segment> segments = flight.getSegments();
            LocalDateTime departureTime;
            LocalDateTime arrivalTime;
            Duration duration = Duration.ZERO;

            for (int i = 1; i < segments.size(); i++) {
                departureTime = segments.get(i).getDepartureDate();
                arrivalTime = segments.get(i - 1).getArrivalDate();
                duration = duration.plus(Duration.between(departureTime, arrivalTime).abs());
            }
            return duration.toHours() >= 2;
        });
        return this;
    }
}