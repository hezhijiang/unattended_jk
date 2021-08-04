package com.gez.woodware.util;

import java.util.Calendar;
import java.util.Date;

public class CalendarUtil {
    public static Date getcsrq(){

        Calendar chusrqCalendar = Calendar.getInstance();
        chusrqCalendar.add(Calendar.YEAR, -30);

        return chusrqCalendar.getTime();


    }
}
