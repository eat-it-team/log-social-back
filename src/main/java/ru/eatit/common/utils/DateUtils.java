package ru.eatit.common.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


public class DateUtils {

    private static final DateTimeFormatter CREATED_AT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");

    public static String getDateWithCreatedAtFormat(long date) {
        LocalDateTime datetime =
                LocalDateTime.ofInstant(Instant.ofEpochMilli(date), ZoneId.systemDefault());
        return CREATED_AT_FORMATTER.format(datetime);
    }
}
