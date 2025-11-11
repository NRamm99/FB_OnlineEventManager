package events;

import java.util.ArrayList;

import users.User;

public class EventHandler {
    private ArrayList<BaseEvent> events = new ArrayList<>();

    public void addEvent(BaseEvent event) {
        events.add(event);
        registerForEvent(event, event.getOrganizer());
    }

    public void removeEvent(Event event) {
        events.remove(event);
    }

    public BaseEvent getEventById(int id) {
        return events.get(id);
    }

    public BaseEvent[] getEvents() {
        return events.toArray(new BaseEvent[0]);
    }

    public void registerForEvent(BaseEvent event, User user) {
        event.getAttendees().add(user);
    }

    public void unregisterForEvent(BaseEvent event, User currentUser) {
        event.getAttendees().remove(currentUser);
    }

}
