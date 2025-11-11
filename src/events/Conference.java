package events;

import users.User;

public class Conference extends BaseEvent {
    public Conference(String eventTitle, String eventDescription, String date, String time, String location,
            User organizer) {
        super(eventTitle, eventDescription, date, time, location, organizer);
    }

    @Override
    public String getEventType() {
        return "conference";
    }
}
