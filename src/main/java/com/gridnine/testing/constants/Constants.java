package com.gridnine.testing.constants;

import java.time.LocalDateTime;

public class Constants {
    // Для удобства пользования время, которое будем принимать за текущий момент, вынесено в отдельный класс в виде константы
    public static final LocalDateTime CURRENT_DATE = LocalDateTime.now().plusDays(3);
}
