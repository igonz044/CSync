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


    }

    public void addEvent() { //takes in a string of information and adds it to the file
        String UID = "";
        //create the Unique ID
        UID = UID + Integer.toString(year);
        UID = UID + Integer.toString(month);
        UID = UID + Integer.toString(day);
        UID = UID + "T";
        currentDate = Calendar.getInstance();
        UID = UID + Integer.toString(currentDate.get(Calendar.HOUR_OF_DAY));
        UID = UID + Integer.toString(currentDate.get(Calendar.MINUTE));
        UID = UID + Integer.toString(currentDate.get(Calendar.SECOND));
        String stamp = UID; //create time stamp
        stamp = stamp + "Z"; //add universal time stamp
        UID = UID + "-CSync";

        TimeZone zone = currentDate.getTimeZone();
        String timeZone = zone.getID(); //get the time zone

        /*VEvent
        date/time: year.month.day.T.hour.minute.second
        20190119T230000
        */
        output.println("Begin:VEvent");
        output.print("UID:");
        output.println(UID);
        output.print("DTSTAMP:");
        output.println(stamp);
        output.print("DTSTART");

        output.print("DTEND");

        output.print("SUMMARY");

        output.println("CLASS:Public");
        output.println("End:VEvent");

    }

    private void compile() { //setup for the calendar file
        output.println("BEGIN:VCALENDAR");

        output.println("END:VCALENDAR");
    }

}
