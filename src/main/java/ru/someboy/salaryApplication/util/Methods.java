package ru.someboy.salaryApplication.util;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public final class Methods {
    public static LocalDateTime stringDateTimeToLocalDateTime(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(dateTime, formatter);
    }

    public static LocalDateTime dateTimeToStartDay(LocalDateTime localDateTime) {
        return localDateTime.with(LocalTime.MIN).plusMinutes(1);
    }

    public static LocalDateTime stringDateTimeToLocalDateTimePlusMin(String dateTime) {
        return dateTimeToStartDay(stringDateTimeToLocalDateTime(dateTime));
    }

    public static Object getPresentOptional(Optional<?> optional) {
        return optional.orElse(null);
    }
}
