package com.ubicomp.tool4work;

import com.google.android.gms.location.places.Place;

import java.util.Date;

/**
 * Created by thiagoyves on 11/06/17.
 */

public class Event {

    private Place place;
    private long arrival_time;
    private long leave_time;
    private String event_name;

    public Event(Place place, long arrival_time, long leave_time, String event_name) {
        this.place = place;
        this.event_name = event_name;
        this.arrival_time = arrival_time;
        this.leave_time = leave_time;
    }

    public long getFinal_date() {
        return leave_time;
    }

    public void setFinal_date(long final_date) {
        this.leave_time = final_date;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public long getInitial_date() {
        return arrival_time;
    }

    public void setInitial_date(long initial_date) {
        this.arrival_time = initial_date;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
}
