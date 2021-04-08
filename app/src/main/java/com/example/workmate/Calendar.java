package com.example.workmate;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Calendar extends AppCompatActivity {

    CompactCalendarView compactCalendar;
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat ("MMM - yyyy", Locale.getDefault());
    private SimpleDateFormat dateFormatHour = new SimpleDateFormat ("HH:mm ", Locale.getDefault());
    TextView mMonth, mEvent;
    final List<Event> classes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        mEvent = findViewById(R.id.events);
        mMonth = findViewById(R.id.month);
        mMonth.setText(dateFormatMonth.format(System.currentTimeMillis()));

        compactCalendar = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        compactCalendar.setUseThreeLetterAbbreviation(true);

        //set an event exam 1st march 9:00
        //convert date to EPOCH timestamp
        final Event ev1 = new Event(Color.BLACK, 1615280400000L, "electrician coming");
        compactCalendar.addEvent(ev1);
        final Event ev3 = new Event(Color.BLACK, 1615464000000L, "plumber coming");
        compactCalendar.addEvent(ev3);

        Date currTime = new Date(System.currentTimeMillis());
        setEvents(currTime);

        compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                Context context = getApplicationContext();
                setEvents(dateClicked);
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                mMonth.setText(dateFormatMonth.format(firstDayOfNewMonth));
            }
        });

    }
    public void setEvents(Date dateClicked){
        List<Event> textEvents = compactCalendar.getEvents(dateClicked);
        String info = "";

        for (Event ev :textEvents) {
            long time = ev.getTimeInMillis();
            Date hour = new Date(time);
            String hr = dateFormatHour.format(hour);
            info = info + hr+": "+ ev.getData().toString() + "\n";
        }
        if (info != "") {
            mEvent.setText(info);
        }else {
            mEvent.setText("No Classes Today");
        }

    }
}