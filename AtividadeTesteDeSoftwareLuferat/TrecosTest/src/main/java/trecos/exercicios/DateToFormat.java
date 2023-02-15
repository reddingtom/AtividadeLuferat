package trecos.exercicios;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateToFormat {

    public static String getDateBr() {
        return new SimpleDateFormat("yyyy/MM/dd").format(new Date());
    }

    public static String getDateSystem() {
        return new SimpleDateFormat("dd-MM-yyyy").format(new Date());
    }
    
    public static String getDateTimeBr() {
        return new SimpleDateFormat("yyyy/MM/dd hh:mm").format(new Date());
    }
    
    public static String getDateTimeSystem() {
        String txtDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        txtDate += " às ";
        txtDate += new SimpleDateFormat("hh:mm").format(new Date());
        return txtDate;
    }
}