import java.util.Scanner;

import events.Event;
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
        Tools.printToConsole("Notifications", true);
        for (String notification : currentUser.getNotifications()) {
            Tools.printToConsole(notification);
        }
        Tools.waitForUser(input);
    }

    private static void promptViewAllEvents() {
        Tools.printToConsole("All Events", true);
        for (Event event : eventHandler.getEvents()) {
            Tools.printToConsole("--------------------------------");
            Tools.printToConsole("Event ID: " + event.getEventId());
            Tools.printToConsole("Event Title: " + event.getEventTitle());
            Tools.printToConsole("Event Description: " + event.getEventDescription());
            Tools.printToConsole("Date: " + event.getDate());
            Tools.printToConsole("Time: " + event.getTime());
            Tools.printToConsole("Location: " + event.getLocation());
            Tools.printToConsole("Attendees: " + event.getAttendees().size());
            Tools.printToConsole(
                    "Organizer: " + event.getOrganizer().getFirstName() + " " + event.getOrganizer().getLastName());
            Tools.printToConsole("--------------------------------\n");
        }
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

    private static void promptUnregisterForEvent() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'promptUnregisterForEvent'");
    }

    private static void promptRegisterForEvent() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'promptRegisterForEvent'");
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

    private static void printMyEvents(User currentUser2) {
        Tools.printToConsole("Events", true);
        for (Event event : eventHandler.getEvents()) {
            if (event.getOrganizer().equals(currentUser)) {
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
        Tools.printToConsole("Enter new event title: ");
    }

    private static void promptCreateEvent() {
        Tools.printToConsole("Create Event", true);
        String eventTitle = Tools.validateName(input, "Event Title");
        String eventDescription = Tools.validateName(input, "Event Description");
        String date = Tools.validateDate(input, "Date");
        String time = Tools.validateTime(input, "Time");
        String location = Tools.validateName(input, "Location");
        eventHandler.addEvent(new Event(eventTitle, eventDescription, date, time, location, currentUser));
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
