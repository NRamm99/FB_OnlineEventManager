package events;

import users.User;

public class MovieNight extends BaseEvent {
    public MovieNight(String eventTitle, String eventDescription, String date, String time, String location,
            User organizer) {
        super(eventTitle, eventDescription, date, time, location, organizer);
    }

    @Override
    public String getEventType() {
        return "movie night";
    }
}
