package events;

import users.User;

public class GameNight extends BaseEvent {
    public GameNight(String eventTitle, String eventDescription, String date, String time, String location,
            User organizer) {
        super(eventTitle, eventDescription, date, time, location, organizer);
    }

    @Override
    public String getEventType() {
        return "game night";
    }
}
