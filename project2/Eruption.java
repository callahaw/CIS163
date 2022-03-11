package project2;

import java.util.Scanner;

/**
 * Write a description of class Eruption here.
 *
 * @author Wade Callahan
 * @version 10/18/2021
 */

public class Eruption
{
    // instance variables - replace the example below with your own
    private int hour, min, day, month, year;
    private String name;

    /**
     * Constructor for objects of class Eruption
     */
    public Eruption(String info){
        Scanner scnr = new Scanner(info);
        scnr.useDelimiter("/|,|:");
        month = scnr.nextInt();
        day = scnr.nextInt();
        year = scnr.nextInt();
        name = scnr.next();
        hour = scnr.nextInt();
        min = scnr.nextInt();
    }

    public String toString(){
        String str = name + " on " + month +"/" + day + "/" + year;
        str += " at " + hour + ":";
        if(min < 10)
            str += "0" + min;
        else
            str += min;
        return str;
    }
    public int getDay(){
        return day;
    }
    public int getMonth(){
        return month;
    }
    public int getYear(){
        return year;
    }
    public int getHour(){
        return hour;
    }
    public int getMinute(){
        return min;
    }
    public String getName(){
        return name;
    }
}
