/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sample.utils;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author Phi Long
 */
public class DateUtils {
    public static boolean checkValidDate(Date date){
        LocalDate localDate = LocalDate.now();
        Date nowDate = java.sql.Date.valueOf(localDate);
        if(date.compareTo(nowDate)>=0){
            return false;
        }
        return true;
    }
}
