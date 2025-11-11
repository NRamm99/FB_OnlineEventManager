package events;

import users.User;

public class EventFactory {

    public static Event createEvent(String eventType, String eventTitle, String eventDescription, String date,
            String time, String location, User organizer) {
        switch (eventType) {
            case "concert":
                return new Concert(eventTitle, eventDescription, date, time, location, organizer);
            case "workshop":
                return new Workshop(eventTitle, eventDescription, date, time, location, organizer);
            case "conference":
                return new Conference(eventTitle, eventDescription, date, time, location, organizer);
            case "party":
                return new Party(eventTitle, eventDescription, date, time, location, organizer);
            case "lan party":
                return new LanParty(eventTitle, eventDescription, date, time, location, organizer);
            case "hackathon":
                return new Hackathon(eventTitle, eventDescription, date, time, location, organizer);
            case "game night":
                return new GameNight(eventTitle, eventDescription, date, time, location, organizer);
            case "movie night":
                return new MovieNight(eventTitle, eventDescription, date, time, location, organizer);
            case "board game night":
                return new BoardGameNight(eventTitle, eventDescription, date, time, location, organizer);
            default:
                return null;
        }
    }
}
