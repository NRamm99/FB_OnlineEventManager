package events;

import java.util.ArrayList;

import users.User;

public class EventHandler {
    private ArrayList<Event> events = new ArrayList<>();

    public void addEvent(Event event) {
        events.add(event);
        registerForEvent(event, event.getOrganizer());
    }

    public void removeEvent(Event event) {
        events.remove(event);
    }

    public Event getEventById(int id) {
        return events.get(id);
    }

    public Event[] getEvents() {
        return events.toArray(new Event[0]);
    }

    public void registerForEvent(Event event, User user) {
        event.getAttendees().add(user);
    }

}
