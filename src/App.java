import java.util.Scanner;

import events.Event;
import events.EventFactory;
import events.EventHandler;
import users.AccountHandler;
import users.User;
import utils.Tools;

public class App {
    private static Scanner input = new Scanner(System.in);
    private static boolean isLoggedIn = false;
    private static AccountHandler accountHandler = new AccountHandler();
    private static EventHandler eventHandler = new EventHandler();
    private static User currentUser = null;

    public static void main(String[] args) {
        while (true) {
            if (!isLoggedIn) {
                promptWelcome();
            }
            if (isLoggedIn) {
                promptMainMenu();
            }
        }
    }

    private static void promptMainMenu() {
        Tools.printToConsole("""
                Main Menu
                Please choose an option:
                1. Create Event
                2. View My Events
                3. View All Events
                4. Notifications
                5. Logout
                6. Exit
                """, true);
        int choice = Tools.validateInt(input, "Choice");
        switch (choice) {
            case 1:
                promptCreateEvent();
                break;
            case 2:
                promptViewMyEvents();
                break;
            case 3:
                promptViewAllEvents();
                break;
            case 4:
                promptViewNotifications();
                break;
            case 5:
                isLoggedIn = false;
                break;
            case 6:
                System.exit(0);
                break;
            default:
                Tools.printToConsole("Invalid choice", true);
                promptMainMenu();
        }
    }

    private static void promptViewNotifications() {
        Tools.printToConsole("Notifications: (" + currentUser.getNotifications().length + ")", true);
        if (currentUser.getNotifications().length == 0) {
            Tools.printToConsole("No notifications", true);
            Tools.waitForUser(input);
            return;
        }
        int notificationCount = 1;
        for (String notification : currentUser.getNotifications()) {
            Tools.printToConsole(notificationCount + ". " + notification);
            notificationCount++;
        }
        Tools.printToConsole("""
                    1... clear notifications
                    2... back
                """);
        int choice = Tools.validateInt(input, "Choice");
        switch (choice) {
            case 1:
                currentUser.clearNotifications();
                Tools.printToConsole("Notifications cleared", true);
                Tools.waitForUser(input);
                break;
            case 2:
                break;
            default:
                Tools.printToConsole("Invalid choice", true);
                Tools.waitForUser(input);
                promptViewNotifications();
                break;
        }
    }

    private static void promptViewAllEvents() {
        printAllEvents();
        Tools.printToConsole("""
                1... register for event
                2... unregister for event
                3... back
                """);
        int choice = Tools.validateInt(input, "Choice");
        switch (choice) {
            case 1:
                promptRegisterForEvent();
                break;
            case 2:
                promptUnregisterForEvent();
                break;
            case 3:
                break;
            default:
                Tools.printToConsole("Invalid choice", true);
                Tools.waitForUser(input);
                promptViewAllEvents();
                break;
        }
    }

    private static void printAllEvents() {
        Tools.printToConsole("All Events", true);
        for (Event event : eventHandler.getEvents()) {
            printEvent(event);
        }
    }

    private static void promptUnregisterForEvent() {
        printAllEvents();
        Tools.printToConsole("Enter event ID: ");
        int eventId = Tools.validateInt(input, "Event ID");
        Event event = eventHandler.getEventById(eventId);
        if (event == null) {
            Tools.printToConsole("Event not found");
            Tools.waitForUser(input);
            return;
        }
        eventHandler.unregisterForEvent(event, currentUser);
        Tools.printToConsole("You are now unregistered for the event: " + event.getEventTitle(), true);
        Tools.printToConsole("You will no longer receive notifications about the event");
        Tools.waitForUser(input);
    }

    private static void promptRegisterForEvent() {
        printAllEvents();
        Tools.printToConsole("Enter event ID: ");
        int eventId = Tools.validateInt(input, "Event ID");
        Event event = eventHandler.getEventById(eventId);
        if (event == null) {
            Tools.printToConsole("Event not found");
            Tools.waitForUser(input);
            return;
        }
        eventHandler.registerForEvent(event, currentUser);
        Tools.printToConsole("You are now registered for the event: " + event.getEventTitle(), true);
        Tools.printToConsole("You will receive notifications about the event");
        Tools.waitForUser(input);
    }

    private static void promptViewMyEvents() {

        printMyEvents(currentUser);
        Tools.printToConsole("""
                1... edit event
                2... back
                """);

        int choice = Tools.validateInt(input, "Choice");
        switch (choice) {
            case 1:
                promptEditEvent();
                break;
            case 2:
                break;
            default:
                Tools.printToConsole("Invalid choice");
                promptViewMyEvents();
                break;
        }
    }

    private static void printMyEvents(User currentUser) {
        Tools.printToConsole("Events", true);
        for (Event event : eventHandler.getEvents()) {
            if (event.getOrganizer().equals(currentUser)) {
                printEvent(event);
            }
        }
    }

    private static void promptEditEvent() {

        Tools.printToConsole("Enter event ID: ");
        int eventId = Tools.validateInt(input, "Event ID");
        Event event = eventHandler.getEventById(eventId);
        if (event == null) {
            Tools.printToConsole("Event not found");
            Tools.waitForUser(input);
            return;
        }
        Tools.clearConsole();
        printEvent(event);
        Tools.printToConsole("""
                1... edit title
                2... edit description
                3... edit date
                4... edit time
                5... edit location
                6... back
                """);
        int choice = Tools.validateInt(input, "Choice");
        switch (choice) {
            case 1:
                promptEditTitle(event);
                break;
            case 2:
                promptEditDescription(event);
                break;
            case 3:
                promptEditDate(event);
                break;
            case 4:
                promptEditTime(event);
                break;
            case 5:
                promptEditLocation(event);
                break;
            case 6:
                break;
            default:
                Tools.printToConsole("Invalid choice", true);
                promptEditEvent();
                break;
        }
    }

    private static void promptEditDescription(Event event) {
        Tools.printToConsole("Enter new event description: ");
        String eventDescription = Tools.validateName(input, "Event Description");
        event.setEventDescription(eventDescription);
        Tools.printToConsole("Event description updated successfully", true);
        Tools.waitForUser(input);
    }

    private static void promptEditDate(Event event) {
        Tools.printToConsole("Enter new event date: ");
        String eventDate = Tools.validateDate(input, "Event Date");
        event.setDate(eventDate);
        Tools.printToConsole("Event date updated successfully", true);
        Tools.waitForUser(input);
    }

    private static void promptEditTime(Event event) {
        Tools.printToConsole("Enter new event time: ");
        String eventTime = Tools.validateTime(input, "Event Time");
        event.setTime(eventTime);
        Tools.printToConsole("Event time updated successfully", true);
        Tools.waitForUser(input);
    }

    private static void promptEditLocation(Event event) {
        Tools.printToConsole("Enter new event location: ");
        String eventLocation = Tools.validateName(input, "Event Location");
        event.setLocation(eventLocation);
        Tools.printToConsole("Event location updated successfully", true);
        Tools.waitForUser(input);
    }

    private static void promptEditTitle(Event event) {
        Tools.printToConsole("Enter new event title: ");
        String eventTitle = Tools.validateName(input, "Event Title");
        event.setEventTitle(eventTitle);
        Tools.printToConsole("Event title updated successfully", true);
        Tools.waitForUser(input);
    }

    private static void printEvent(Event event) {
        Tools.printToConsole("----------- " + event.getEventType().toUpperCase() + " -----------");
        Tools.printToConsole("Event ID: " + event.getEventId());
        Tools.printToConsole("Event Title: " + event.getEventTitle());
        Tools.printToConsole("Event Description: " + event.getEventDescription());
        Tools.printToConsole("Date: " + event.getDate());
        Tools.printToConsole("Time: " + event.getTime());
        Tools.printToConsole("Location: " + event.getLocation());
        Tools.printToConsole("Attendees: " + event.getAttendees().size());
        Tools.printToConsole(
                "Organizer: " + event.getOrganizer().getFirstName() + " " + event.getOrganizer().getLastName());
        Tools.printToConsole("--------------------------------");
    }

    private static void promptCreateEvent() {
        Tools.printToConsole("Create Event", true);
        Tools.printToConsole("""
                1... concert
                2... workshop
                3... conference
                4... party
                5... lan party
                6... hackathon
                7... game night
                8... movie night
                9... board game night
                """);
        int choice = Tools.validateInt(input, "Choice");
        String eventType = "";
        switch (choice) {
            case 1:
                eventType = "concert";
                break;
            case 2:
                eventType = "workshop";
                break;
            case 3:
                eventType = "conference";
                break;
            case 4:
                eventType = "party";
                break;
            case 5:
                eventType = "lan party";
                break;
            case 6:
                eventType = "hackathon";
                break;
            case 7:
                eventType = "game night";
                break;
            case 8:
                eventType = "movie night";
                break;
            case 9:
                eventType = "board game night";
                break;
            default:
                Tools.printToConsole("Invalid choice", true);
                promptCreateEvent();
                break;
        }
        String eventTitle = Tools.validateString(input, "Event Title");
        String eventDescription = Tools.validateString(input, "Event Description");
        String date = Tools.validateDate(input, "Date");
        String time = Tools.validateTime(input, "Time");
        String location = Tools.validateString(input, "Location");
        eventHandler.addEvent(
                EventFactory.createEvent(eventType, eventTitle, eventDescription, date, time, location, currentUser));
        Tools.printToConsole("Event created successfully", true);
        Tools.waitForUser(input);
    }

    private static void promptWelcome() {
        Tools.printToConsole("""
                Welcome to EasyEvent!
                Please choose an option:
                1. Login
                2. Register
                3. Exit
                """, true);
        int choice = Tools.validateInt(input, "Choice");
        switch (choice) {
            case 1:
                promptLogin();
                break;
            case 2:
                promptRegister();
                break;
            case 3:
                System.exit(0);
                break;
            default:
                Tools.printToConsole("Invalid choice", true);
                promptWelcome();
        }
    }

    private static void promptRegister() {
        Tools.printToConsole("Please enter your username: ", true);
        String username = input.nextLine().trim();
        if (accountHandler.getUserByUsername(username) != null) {
            Tools.printToConsole("Username already exists");
            Tools.waitForUser(input);
            return;
        }
        Tools.printToConsole("Please enter your password: ", true);
        String password = input.nextLine();
        Tools.printToConsole("Please enter your first name: ", true);
        String firstName = input.nextLine().trim();
        Tools.printToConsole("Please enter your last name: ", true);
        String lastName = input.nextLine().trim();
        accountHandler.register(username, password, firstName, lastName);

        Tools.printToConsole("Registration successful", true);
        Tools.waitForUser(input);
    }

    private static void promptLogin() {
        Tools.printToConsole("Please enter your username: ", true);
        User user = accountHandler.getUserByUsername(input.nextLine());
        if (user == null) {
            Tools.printToConsole("User not found");
            Tools.waitForUser(input);
            return;
        }
        Tools.printToConsole("Please enter your password: ", true);
        String password = input.nextLine();
        isLoggedIn = accountHandler.login(user, password);
        if (isLoggedIn) {
            Tools.printToConsole("Welcome back, " + user.getFirstName() + "!", true);
            currentUser = user;
        } else {
            Tools.printToConsole("Wrong password", true);
            Tools.waitForUser(input);
        }
    }
}
