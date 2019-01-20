package com.example.csync;



import android.content.Intent;
import android.provider.CalendarContract;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.TimeZone;

public class fileBuilder {

    private File fileName;
    private PrintWriter output;
    private Calendar currentDate;
    private String info;
    private int year;
    private int month;
    private int day;

    //Constructor for the fileBuilder class, attempts to open a file to write the .ics information into
    public fileBuilder(String data) { //create the file
        info = data;
        //System.out.println("Start");
        currentDate = Calendar.getInstance();
        year = currentDate.get(Calendar.YEAR);
        month = currentDate.get(Calendar.MONTH);
        day = currentDate.get(Calendar.DAY_OF_MONTH);
        fileName = new File("/data/data/com.example.csync", "exportFile.ics");
        try {
            //System.out.println("through");
            output = new PrintWriter(fileName);
            output.println("BEGIN:VCALENDAR");
            output.println("VERSION:2.0");
            output.println("CALSCALE:GREGORIAN");
            output.println("PRODID:-//CSync");
            //this.addEvent();
            this.export();
            //System.out.println("completed");
        } catch (IOException e) {
            //System.out.println("Caught");
        //errors
        }

    }

    public void addEvent() { //takes in a string of information and adds it to the file
        String UID = getCurrentDate(); //create unique ID
        String stamp = UID; //create time stamp

        stamp = stamp + "Z"; //add universal time stamp
        UID = UID + "-CSync"; //add CSync stamp

        String timeZone = this.getTimeZone();
        timeZone = timeZone + ":";

        /*VEvent
        date/time: year.month.day.T.hour.minute.second
        20190119T230000
        */
        //Unique ID
        output.println("BEGIN:VEVENT");
        output.print("UID:");
        output.println(UID);
        //Creation time stamp
        output.print("DTSTAMP:");
        output.println(stamp);
        //Start time
        output.print("DTSTART;TZID=");
        output.print(timeZone);
        output.println(getStart(info));
        //End time
        output.print("DTEND;TZID=");
        output.print(timeZone);
        output.println(getEnd(info));
        //Summary
        output.print("SUMMARY:");
        output.println(getSummary(info));

        output.println("CLASS:Public");
        output.println("END:VEVENT");

    }
    //Return: intent that sends message to calendar app to save an event
    //Finalizes .ics file and creates a single event for testing purposes
    public Intent export() { //setup for the calendar file
        output.print("END:VCALENDAR");
        if(output != null) {
            output.close();
        }

        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setData(CalendarContract.Events.CONTENT_URI);
        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, getStart(info).getTimeInMillis());
        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, getEnd(info).getTimeInMillis());
        intent.putExtra(CalendarContract.Events.TITLE, getSummary(info));
        System.out.println("finsihed");
        return intent;
    }

    //String info: the string with data on the event start date
    //Return: A Calendar object derived from passed info
    private Calendar getStart(String info) {
        Calendar beginTime = Calendar.getInstance();
        beginTime.set(2019, 0, 24, 18, 00);
        return beginTime;
    }

    //String info: the string with data on the event end date
    //Return: A Calendar object derived from passed info
    private Calendar getEnd(String info) {
        Calendar endTime = Calendar.getInstance();
        endTime.set(2019, 0, 24, 19, 00);
        return endTime;
    }

    //String info: the string with data on the event description
    //Return: A String object derived from passed info
    private String getSummary(String info) {
        if(info.isEmpty()) {
            return "Empty";
        }
        else {
            return "Meet with Greg";
        }
    }

    //Return: A String with the current city and time zone
    private String getTimeZone() {
        TimeZone zone = currentDate.getTimeZone();
        return zone.getID();
    }

    //Return: Gives the current date and time split by "T" with no spaces
    private String getCurrentDate() {
        String CD = "";
        CD = CD + Integer.toString(year);
        CD = CD + this.month();
        CD = CD + this.day();
        CD = CD + "T";
        currentDate = Calendar.getInstance();
        CD = CD + this.hour();
        CD = CD + this.minute();
        CD = CD + this.second();
        return CD;
    }

    //Return: The numerical value of the current month as a String with two digits
    private String month() {
        month = month + 1;
        String m;
        if(month < 10) {
            m = "0" + Integer.toString(month);
        } else {
            m = Integer.toString(month);
        }
        return m;
    }

    //Return: The current day as a String with two digits
    private String day() {
        String d;
        if(day < 10) {
            d = "0" + Integer.toString(day);
        } else {
            d = Integer.toString(day);
        }
        return d;
    }

    //Return: The current hour with two digits
    private String hour() {
        String h;
        if(currentDate.get(Calendar.HOUR_OF_DAY) < 10) {
            h = "0" + Integer.toString(currentDate.get(Calendar.HOUR_OF_DAY));
        } else {
            h = Integer.toString(currentDate.get(Calendar.HOUR_OF_DAY));
        }
        return h;
    }

    //Return: The current minute with two digits
    private String minute() {
        String m;
        if(currentDate.get(Calendar.MINUTE) < 10) {
            m = "0" + Integer.toString(currentDate.get(Calendar.MINUTE));
        } else {
            m = Integer.toString(currentDate.get(Calendar.MINUTE));
        }
        return m;
    }

    //Return: The current second with two digits
    private String second() {
        String s;
        if(currentDate.get(Calendar.SECOND) < 10) {
            s = "0" + Integer.toString(currentDate.get(Calendar.SECOND));
        } else {
            s = Integer.toString(currentDate.get(Calendar.SECOND));
        }
        return s;
    }
}
