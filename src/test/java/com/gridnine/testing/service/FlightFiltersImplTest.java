package com.gridnine.testing.service;

import com.gridnine.testing.entity.Flight;
import com.gridnine.testing.entity.Segment;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

class FlightFiltersImplTest {

    // Установка текущей даты
    private static final LocalDateTime CURRENT_DATE = LocalDateTime.now().plusDays(3);

    // Создание тестовых перелетов:
    // 1. Перелет длительностью 3 часа
    public static Flight normalFlight = new Flight(List.of(
            new Segment(CURRENT_DATE, CURRENT_DATE.plusHours(3))
    ));

    // 2. Перелет, состоящий из двух сегментов
    public static Flight doubleSegmentFlight = new Flight(List.of(
            new Segment(CURRENT_DATE, CURRENT_DATE.plusHours(2)),
            new Segment(CURRENT_DATE.plusHours(3), CURRENT_DATE.plusHours(4))
    ));

    // 3. Перелеты с датой вылета раньше текущего времени
    public static  Flight departureTimeInThePast1 = new Flight(List.of(
            new Segment(CURRENT_DATE.minusDays(1), CURRENT_DATE)
    ));
    public static  Flight departureTimeInThePast2 = new Flight(List.of(
            new Segment(CURRENT_DATE.minusDays(2), CURRENT_DATE)
    ));

    // 4. Перелеты с датой прибытия раньше чем дата вылета
    public static  Flight arrivalTimeBeforeDeparture1 = new Flight(List.of(
            new Segment(CURRENT_DATE, CURRENT_DATE.minusHours(4))
    ));
    public static  Flight arrivalTimeBeforeDeparture2 = new Flight(List.of(
            new Segment(CURRENT_DATE, CURRENT_DATE.minusHours(6))
    ));

    // 5. Перелеты с длительностью пересадки более двух часов
    public static  Flight landingMoreThenTwoHours1 = new Flight(List.of(
            new Segment(CURRENT_DATE, CURRENT_DATE.plusHours(2)),
            new Segment(CURRENT_DATE.plusHours(6), CURRENT_DATE.plusHours(8))
    ));
    public static  Flight landingMoreThenTwoHours2 = new Flight(List.of(
            new Segment(CURRENT_DATE, CURRENT_DATE.plusHours(4)),
            new Segment(CURRENT_DATE.plusHours(8), CURRENT_DATE.plusHours(10))
    ));

    // Создание актуальных списков перелетов
    public static  List<Flight> actualListByDepartureInThePast = Arrays.asList(
            departureTimeInThePast1,
            departureTimeInThePast2
    );
    public static  List<Flight> actualListByArrivalTimeBeforeDeparture = Arrays.asList(
            arrivalTimeBeforeDeparture1,
            arrivalTimeBeforeDeparture2
    );
    public static  List<Flight> actualListByLandingMoreThenTwoHours = Arrays.asList(
            landingMoreThenTwoHours1,
            landingMoreThenTwoHours2
    );

    // Тесты

    // Вылет до текущего момента времени
    @Test
    public void departureTimeBeforeNowFilterTest() {
        FlightFilters flightFilters = new FlightFiltersImpl(actualListByDepartureInThePast);

        List<Flight> filteredFlights = flightFilters
                .departureTimeBeforeNowFilter()
                .build();
        assertTrue(filteredFlights.isEmpty());
    }

    // Сегменты с датой прилёта раньше даты вылета
    @Test
    public void arrivalEarlierThenDepartureFilterTest() {
        FlightFilters flightFilters = new FlightFiltersImpl(actualListByArrivalTimeBeforeDeparture);

        List<Flight> filteredFlights = flightFilters
                .arrivalEarlierThenDepartureFilter()
                .build();
        assertTrue(filteredFlights.isEmpty());
    }

    // Перелеты, где общее время, проведённое на земле, превышает два часа
    @Test
    public void landingTimeMoreThenTwoHoursFilterTest() {
        FlightFilters flightFilters = new FlightFiltersImpl(actualListByLandingMoreThenTwoHours);

        List<Flight> filteredFlights = flightFilters
                .landingTimeMoreThenTwoHoursFilter()
                .build();
        assertTrue(filteredFlights.isEmpty());
    }
}