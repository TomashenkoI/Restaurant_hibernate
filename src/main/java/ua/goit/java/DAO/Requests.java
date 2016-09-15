package ua.goit.java.DAO;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by 7 on 22.08.2016.
 */
public class Requests {

    public String getCurrentTime(){

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String time = sdf.format(cal.getTime());

        Date date = new Date(System.currentTimeMillis());

        String year = date.toString().substring(0, 4);;
        String day = date.toString().substring(8, 10);;
        String month = date.toString().substring(5, 7);;

        return day + "." + month + "." + year + " " + time;
    }

}
