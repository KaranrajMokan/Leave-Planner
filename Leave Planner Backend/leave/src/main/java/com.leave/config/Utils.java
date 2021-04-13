package com.leave.config;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class Utils {

    public static int calculateDuration(LocalDate startDate, LocalDate endDate) {
        int duration = 0;
        LocalDate date;
        for (date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            if ((calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY)) {
                duration += 1;
            }
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        if ((calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY)) {
            duration += 1;
        }
        return duration;
    }

}
