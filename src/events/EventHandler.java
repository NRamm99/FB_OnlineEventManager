package events;

import java.util.ArrayList;

public class EventHandler {
    private ArrayList<Event> events = new ArrayList<>();

    public void addEvent(Event event) {
        events.add(event);
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

}
