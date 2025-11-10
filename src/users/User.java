package users;

import java.util.ArrayList;

import events.Observer;

public class User implements Observer {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private ArrayList<String> notifications;

    public User(String username, String password, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.notifications = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public void addNotification(String message) {
        notifications.add(message);
    }

    public String[] getNotifications() {
        return notifications.toArray(new String[notifications.size()]);
    }

    public void clearNotifications() {
        notifications.clear();
    }
}
