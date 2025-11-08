package users;
import java.util.ArrayList;

public class AccountHandler {
    private ArrayList<User> users = new ArrayList<>();

    public AccountHandler() {
        this.users = new ArrayList<>();
        addUser(new User("admin", "admin", "Admin", "Admin"));
        addUser(new User("brugernavn", "kode123", "Nichlas", "Ramm"));
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
        if (user.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    public void register(String username, String password, String firstName, String lastName) {
        addUser(new User(username, password, firstName, lastName));
    }
}
