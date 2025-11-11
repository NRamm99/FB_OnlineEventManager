package users;

import java.util.ArrayList;

public class AccountHandler {
    private ArrayList<User> users = new ArrayList<>();

    public AccountHandler() {
        this.users = new ArrayList<>();
        addUser(new User("admin", "admin", "Admin", "Bruger"));
        addUser(new User("Nichlas", "kode", "Nichlas", "Ramm"));
    }

    private void addUser(User user) {
        users.add(user);
    }

    public User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }
        return null;
    }

    public boolean login(User user, String password) {
        return user.getPassword().equals(password);
    }

    public void register(String username, String password, String firstName, String lastName) {
        addUser(new User(username, password, firstName, lastName));
    }
}
