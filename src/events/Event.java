package events;

import java.util.List;

import users.User;

public interface Event {

    public String getEventTitle();

    public String getEventDescription();

    public String getDate();

    public String getTime();

    public String getLocation();

    public User getOrganizer();

    public int getEventId();

    public List<User> getAttendees();

    public void setEventTitle(String eventTitle);

    public void setEventDescription(String eventDescription);

    public void notifyAttendees(String message);

    public void setLocation(String eventLocation);

    public void setTime(String eventTime);

    public void setDate(String eventDate);

    public String getEventType();
}
