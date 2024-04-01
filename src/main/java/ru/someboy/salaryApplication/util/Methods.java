package ru.someboy.salaryApplication.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class Methods {
    public static LocalDateTime stringDateTimeToLocalDateTime(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(dateTime, formatter);
    }

    public static LocalDateTime dateTimeToStartDayPlusMin(LocalDateTime localDateTime) {
        return localDateTime.with(LocalTime.MIN).plusMinutes(1);
    }

    public static LocalDateTime dateTimeToEndDayMinusNano(LocalDateTime localDateTime) {
        return localDateTime.with(LocalTime.MAX).minusNanos(1);
    }

//    public static LocalDateTime dateTimeToEndDayMinusNano(LocalDate localDate) {
//        return localDate.with(LocalTime.MAX).plusDays(1).atStartOfDay();
//    }

    public static LocalDateTime stringDateTimeToLocalDateTimePlusMin(String dateTime) {
        return dateTimeToStartDayPlusMin(stringDateTimeToLocalDateTime(dateTime));
    }

    public static List<LocalDateTime> startAndEndDateTimeList(LocalDateTime dateTime) {
        List<LocalDateTime> dateTimeList = new ArrayList<>();
        dateTimeList.add(0, dateTimeToStartDayPlusMin(dateTime.withDayOfMonth(1)));
        dateTimeList.add(1, dateTimeToEndDayMinusNano(dateTime.toLocalDate().with(TemporalAdjusters.lastDayOfMonth()).atStartOfDay()).minusNanos(1));
        return dateTimeList;
    }

    public static List<LocalDateTime> startAndEndDateTimeListHalfMonth(LocalDateTime dateTime) {
        List<LocalDateTime> dateTimeList = new ArrayList<>();
        if(dateTime.getDayOfMonth() > 15) {
            dateTimeList.add(0, dateTimeToStartDayPlusMin(dateTime.withDayOfMonth(16)));
            dateTimeList.add(1, dateTimeToEndDayMinusNano(dateTime.toLocalDate().with(TemporalAdjusters.lastDayOfMonth()).atStartOfDay()).minusNanos(1));
        } else {
            dateTimeList.add(0, dateTimeToStartDayPlusMin(dateTime.withDayOfMonth(1)));
            dateTimeList.add(1, dateTimeToStartDayPlusMin(dateTime.withDayOfMonth(15)));
        }
        return dateTimeList;
    }

    public static Object getPresentOptional(Optional<?> optional) {
        return optional.orElse(null);
    }
}
