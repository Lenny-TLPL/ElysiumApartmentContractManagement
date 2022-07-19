/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sample.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 *
 * @author Phi Long
 */
public class DateUtils {

    public static boolean checkValidDate(Date date) {
        LocalDate localDate = LocalDate.now();
        Date nowDate = java.sql.Date.valueOf(localDate);
        if (date.compareTo(nowDate) > 0) {
            return false;
        }
        return true;
    }

    public static boolean compareTwoDate(Date date1, Date date2) {
        if (date1.compareTo(date2) >= 0) {
            return true;
        }
        return false;
    }

    public static long subtractTwoDay(Date startDate, Date endDate) {
        long difference_In_Time
                = endDate.getTime() - startDate.getTime();
        long difference_In_Years
                = (difference_In_Time
                / (1000 * 60 * 60 * 24 * 365));
        return difference_In_Years;
    }

    public static Date plusMonths(Date date, int months) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        dateTime = dateTime.plusMonths(months);
        ZonedDateTime zdt = dateTime.atZone(ZoneId.systemDefault());
        Date output = Date.from(zdt.toInstant());
        return output;       
    }

}
