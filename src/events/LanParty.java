package events;

import users.User;

public class LanParty extends BaseEvent {
    public LanParty(String eventTitle, String eventDescription, String date, String time, String location,
            User organizer) {
        super(eventTitle, eventDescription, date, time, location, organizer);
    }

    @Override
    public String getEventType() {
        return "lan party";
    }
}
