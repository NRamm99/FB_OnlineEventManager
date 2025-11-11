package events;

import java.util.ArrayList;
import java.util.List;

import users.User;

public class Party implements Event {
    private int eventId;
    private String eventTitle;
    private String eventDescription;
    private String date;
    private String time;
    private String location;
    private User organizer;
    private ArrayList<User> attendees;
    private static int idCounter = 0;

    public Party(String eventTitle, String eventDescription, String date, String time, String location,
            User organizer) {
        this.eventId = idCounter++;
        this.eventTitle = eventTitle;
        this.eventDescription = eventDescription;
        this.date = date;
        this.time = time;
        this.location = location;
        this.organizer = organizer;
        this.attendees = new ArrayList<>();
    }

    @Override
    public String getEventTitle() {
        return eventTitle;
    }

    @Override
    public String getEventDescription() {
        return eventDescription;
    }

    @Override
    public String getDate() {
        return date;
    }

    @Override
    public String getTime() {
        return time;
    }

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public User getOrganizer() {
        return organizer;
    }

    @Override
    public int getEventId() {
        return eventId;
    }

    @Override
    public List<User> getAttendees() {
        return attendees;
    }

    @Override
    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
        notifyAttendees("(" + eventTitle + ") The event title has been changed to: " + eventTitle);
    }

    @Override
    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
        notifyAttendees("(" + eventTitle + ") The event description has been changed to: " + eventDescription);
    }

    @Override
    public void notifyAttendees(String message) {
        for (User attendee : attendees) {
            attendee.addNotification(message);
        }
    }

    @Override
    public void setLocation(String eventLocation) {
        this.location = eventLocation;
        notifyAttendees("(" + eventTitle + ") The event location has been changed to: " + eventLocation);
    }

    @Override
    public void setTime(String eventTime) {
        this.time = eventTime;
        notifyAttendees("(" + eventTitle + ") The event time has been changed to: " + eventTime);
    }

    @Override
    public void setDate(String eventDate) {
        this.date = eventDate;
        notifyAttendees("(" + eventTitle + ") The event date has been changed to: " + eventDate);
    }

    @Override
    public String getEventType() {
        return "party";
    }
}
