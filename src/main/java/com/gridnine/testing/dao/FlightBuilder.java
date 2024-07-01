package com.gridnine.testing.dao;

import com.gridnine.testing.entity.Flight;
import com.gridnine.testing.entity.Segment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.gridnine.testing.constants.Constants.CURRENT_DATE;

// Конфигуратор перелетов по заданным параметрам
public class FlightBuilder {
    public static List<Flight> createFlights() {
        return Arrays.asList(
                // Перелет длительностью 2 часа
                createFlight(
                        CURRENT_DATE, CURRENT_DATE.plusHours(2)),
                // Перелет, состоящий из двух сегментов
                createFlight(
                        CURRENT_DATE, CURRENT_DATE.plusHours(2),
                        CURRENT_DATE.plusHours(3), CURRENT_DATE.plusHours(5)),
                // Перелет с датой отправления раньше чем текущая дата
                createFlight(
                        CURRENT_DATE.minusDays(6), CURRENT_DATE),
                // Перелет с датой прибытия раньше чем дата отправления
                createFlight(
                        CURRENT_DATE, CURRENT_DATE.minusHours(6)),
                // Перелет с временем пересадки более двух часов
                createFlight(
                        CURRENT_DATE, CURRENT_DATE.plusHours(2),
                        CURRENT_DATE.plusHours(5), CURRENT_DATE.plusHours(6)),
                // Еще один перелет с временем пересадки более двух часов
                createFlight(
                        CURRENT_DATE, CURRENT_DATE.plusHours(2),
                        CURRENT_DATE.plusHours(3), CURRENT_DATE.plusHours(4),
                        CURRENT_DATE.plusHours(6), CURRENT_DATE.plusHours(7)));
    }

    private static Flight createFlight(final LocalDateTime... dates) {
        if ((dates.length % 2) != 0) {
            throw new IllegalArgumentException(
                    "you must pass an even number of dates");
        }
        List<Segment> segments = new ArrayList<>(dates.length / 2);
        for (int i = 0; i < (dates.length - 1); i += 2) {
            segments.add(new Segment(dates[i], dates[i + 1]));
        }
        return new Flight(segments);
    }
}
