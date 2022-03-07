package com.titilope.web_inventory.AjokeStores.Utilities;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    public static Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date asDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate asLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDateTime asLocalDateTime(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static Date startOfDayAsDate(Date date){
        LocalDate localDate = asLocalDate(date);
        LocalDateTime startOfDayAsLocalDateTime = localDate.atStartOfDay();
        return asDate(startOfDayAsLocalDateTime);
    }

    public static Date parseStringToDate (String dateString){
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm", Locale.ENGLISH);
        LocalDateTime date = LocalDateTime.parse(dateString, formatter);
        return asDate(date);
    }

    public static Date parseStringToDateOnly (String dateString) throws Exception{
        SimpleDateFormat formatter =
                new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(dateString);
        return date;
    }
}
