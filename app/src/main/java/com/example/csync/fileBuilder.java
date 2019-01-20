package com.example.csync;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.TimeZone;

public class fileBuilder {

    private File fileName;
    private PrintWriter output;
    private Calendar currentDate;
    private int year;
    private int month;
    private int day;

    public fileBuilder() { //create the file
        currentDate = Calendar.getInstance();
        year = currentDate.get(Calendar.YEAR);
        month = currentDate.get(Calendar.MONTH);
        day = currentDate.get(Calendar.DAY_OF_MONTH);

        fileName = new File("exportFile.ics");
        try {
            output = new PrintWriter(fileName);
        } catch (IOException e) {
            //errors
        }
        output.println("BEGIN:VCALENDAR");
        output.println("VERSION:2.0");
        output.println("CALSCALE:GREGORIAN");
        output.println("PRODID:-//CSync");

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
        output.println("Begin:VEvent");
        output.print("UID:");
        output.println(UID);
        //Creation time stamp
        output.print("DTSTAMP:");
        output.println(stamp);
        //Start time
        output.print("DTSTART;TZID=");
        output.print(timeZone);
        output.println(getStart());
        //End time
        output.print("DTEND;TZID=");
        output.print(timeZone);
        output.println(getEnd());
        //Summary
        output.print("SUMMARY");
        output.println(getSummary());

        output.println("CLASS:Public");
        output.println("End:VEvent");

    }

    public void export() { //setup for the calendar file
        output.print("END:VCALENDAR");
        output.close();
    }

    private String getStart() {
        return "";
    }

    private String getEnd() {
        return "";
    }

    private String getSummary() {
        return "";
    }

    private String getTimeZone() {
        TimeZone zone = currentDate.getTimeZone();
        return zone.getID();
    }

    private String getCurrentDate() {
        String CD = "";
        CD = CD + Integer.toString(year);
        CD = CD + Integer.toString(month);
        CD = CD + Integer.toString(day);
        CD = CD + "T";
        currentDate = Calendar.getInstance();
        CD = CD + Integer.toString(currentDate.get(Calendar.HOUR_OF_DAY));
        CD = CD + Integer.toString(currentDate.get(Calendar.MINUTE));
        CD = CD + Integer.toString(currentDate.get(Calendar.SECOND));
        return CD;
    }
}
