package Helper;

import java.text.DecimalFormat;

public class Util {

    public static void main(String[] args) {
        System.out.println(getTimeInMinutesSeconds(300.0));
        System.out.println(getTimeInHoursMinutesSeconds(300.0));
        System.out.println(getTimeInMinutesSeconds(1369.17));
        System.out.println(getTimeInMinutesSeconds(3600.0));
        System.out.println(getTimeInHoursMinutesSeconds(3600.0));
    }

    public static String getPercentage(Double value) {
        return new DecimalFormat("#.##").format(value) + "%";
    }

    public static String getTimeInMinutesSeconds(Double value) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%02d",(int)(value / 60)));
        sb.append(":");
        sb.append(String.format("%02d", (int)(value % 60)));
        return sb.toString();
    }

    public static String getTimeInHoursMinutesSeconds(Double value) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%02d",(int)(value/3600)));
        sb.append(":");
        sb.append(String.format("%02d", (int)((value/60) % 60)));
        sb.append(":");
        sb.append(String.format("%02d", (int)(value % 60)));
        return sb.toString();
    }

    public static String getProperTimeFormat(Double value) {
        if(value >= 3600) return getTimeInHoursMinutesSeconds(value);
        return getTimeInMinutesSeconds(value);
    }
}
