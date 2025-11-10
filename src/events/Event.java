package events;

import java.util.ArrayList;
import java.util.List;

import users.User;

public class Event {
    private int eventId;
    private String eventTitle;
    private String eventDescription;
    private String date;
    private String time;
    private String location;
    private User organizer;
    private ArrayList<User> attendees;
    private static int idCounter = 0;

    public Event(String eventTitle, String eventDescription, String date, String time, String location,
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

    public String getEventTitle() {
        return eventTitle;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getLocation() {
        return location;
    }

    public User getOrganizer() {
        return organizer;
    }

    public int getEventId() {
        return eventId;
    }

    public List<User> getAttendees() {
        return attendees;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
        notifyAttendees("The event title has been changed to: " + eventTitle);
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
        notifyAttendees("The event description has been changed to: " + eventDescription);
    }

    public void notifyAttendees(String message) {
        for (User attendee : attendees) {
            attendee.addNotification(message);
        }
    }
}
