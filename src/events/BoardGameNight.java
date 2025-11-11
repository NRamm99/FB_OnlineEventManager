package events;

import users.User;

public class BoardGameNight extends BaseEvent {
    public BoardGameNight(String eventTitle, String eventDescription, String date, String time, String location,
            User organizer) {
        super(eventTitle, eventDescription, date, time, location, organizer);
    }

    @Override
    public String getEventType() {
        return "board game night";
    }
}
