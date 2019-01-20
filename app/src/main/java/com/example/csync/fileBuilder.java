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

    private Calendar getStart(String info) {
        Calendar beginTime = Calendar.getInstance();
        beginTime.set(2019, 0, 24, 18, 00);
        return beginTime;
    }

    private Calendar getEnd(String info) {
        Calendar endTime = Calendar.getInstance();
        endTime.set(2019, 0, 24, 19, 00);
        return endTime;
    }

    private String getSummary(String info) {
        if(info.isEmpty()) {
            return "Empty";
        }
        else {
            return "Meet with Greg";
        }
    }

    private String getTimeZone() {
        TimeZone zone = currentDate.getTimeZone();
        return zone.getID();
    }

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

    private String day() {
        String d;
        if(day < 10) {
            d = "0" + Integer.toString(day);
        } else {
            d = Integer.toString(day);
        }
        return d;
    }

    private String hour() {
        String h;
        if(currentDate.get(Calendar.HOUR_OF_DAY) < 10) {
            h = "0" + Integer.toString(currentDate.get(Calendar.HOUR_OF_DAY));
        } else {
            h = Integer.toString(currentDate.get(Calendar.HOUR_OF_DAY));
        }
        return h;
    }

    private String minute() {
        String m;
        if(currentDate.get(Calendar.MINUTE) < 10) {
            m = "0" + Integer.toString(currentDate.get(Calendar.MINUTE));
        } else {
            m = Integer.toString(currentDate.get(Calendar.MINUTE));
        }
        return m;
    }

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
