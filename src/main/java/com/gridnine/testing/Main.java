package com.gridnine.testing;

import com.gridnine.testing.dao.FlightBuilder;
import com.gridnine.testing.entity.Flight;
import com.gridnine.testing.service.FlightFiltersImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Список всех перелетов
        List<Flight> flights = FlightBuilder.createFlights();

        // Фильтр по дате вылета до текущей даты
        List<Flight> flightsWithDepartureTimeBeforeNow = new FlightFiltersImpl(flights)
                .departureTimeBeforeNowFilter()
                .build();

        // Фильтр по дате прилета до даты вылета
        List<Flight> flightsWithArrivalEarlierThenDeparture = new FlightFiltersImpl(flights)
                .arrivalEarlierThenDepartureFilter()
                .build();

        // Фильтр по длительности пересадки более двух часов
        List<Flight> flightsWithLandingTimeMoreThenTwoHours = new FlightFiltersImpl(flights)
                .landingTimeMoreThenTwoHoursFilter()
                .build();

        // Выводы в консоль
        System.out.println("All flights before filtering:\n" + flights);
        System.out.println("\nFlights with departure time before now:\n" + flightsWithDepartureTimeBeforeNow);
        System.out.println("\nFlights with arrival earlier then departure:\n" + flightsWithArrivalEarlierThenDeparture);
        System.out.println("\nFlights with landing time more then two hours:\n" + flightsWithLandingTimeMoreThenTwoHours);
    }
}