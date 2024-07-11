package p3;

import p2.TradableDTO;
import java.util.HashMap;
import java.util.Random;

public class UserManager {
    private static UserManager instance;

    // HashMap for users
    private final HashMap<String, User> users;

    // constructor
    private UserManager() {
        users = new HashMap<>();
    }

    // getter method for instance
    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    // usermanager initialized with array of ids
    public void init(String[] usersIn) {
        // check if array is null
        if (usersIn == null) {
            throw new DataValidationException("User array cannot be null");
        }
        // loop thourgh each id in array,
        for (String userId : usersIn) {
            // create user object with current id
            User user = new User(userId);
            // add to HashMap with id as key
            users.put(userId, user);
        }
    }

    // get random user from UserManager's suers
    public User getRandomUser() {
        // check is hashmp is empty
        if (users.isEmpty()) {
            return null;
        }
        Object[] userArray = users.values().toArray();
        int randomIndex = new Random().nextInt(userArray.length);
        return (User) userArray[randomIndex];
    }

    // add tradable to certain user
    public void addToUser(String userId, TradableDTO o) {
        // check if id is valid
        if (userId == null) {
            throw new DataValidationException("User ID cannot be null");
        }
        // check if tradable is valid
        if (o == null) {
            throw new DataValidationException("TradableDTO cannot be null");
        }
        // get user with the certain idfrom hashmap
        User user = users.get(userId);
        if (user == null) {
            throw new DataValidationException("User does not exist");
        }
        user.addTradable(o);
    }

    // get user by their id
    public User getUser(String id) {
        return users.get(id);
    }

    // toString
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (User user : users.values()) {
            sb.append(user.toString()).append("\n");
        }
        return sb.toString();
    }
}
