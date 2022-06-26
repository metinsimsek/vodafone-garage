package com.garage.model;

public class Events {

    private String event;

    public Events(String event) {
        super();
        this.event = event;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "Events{" +
                "event='" + event + '\'' +
                '}';
    }
}
