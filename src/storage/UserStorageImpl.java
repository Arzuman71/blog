package storage;

import model.User;

import java.util.ArrayList;
import java.util.List;

public class UserStorageImpl {

    private List<User> users = new ArrayList<>();

    public void add(User user) {

        users.add(user);

    }


    public User login(String email, String passwords) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getEmail().equals(email) && users.get(i).getPassword().equals(passwords)) {
                return users.get(i);
            }
        }
        return null;
    }

    public boolean isEmpty() {
        return users.isEmpty();
    }
}
