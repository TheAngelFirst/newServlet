package ru.appline.logic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Model implements Serializable {
    private static final Model INSTANCE = new Model();
    private final Map<Integer, User> model;

    public static Model getINSTANCE() {
        return INSTANCE;
    }

    private Model() {
        model = new HashMap<>();
        model.put(1, new User("Alexey", "Shiryaev", "50000"));
        model.put(2, new User("Evgeniy", "Velichko", "60000"));
        model.put(3, new User("Alexey", "Kuznetsov", "70000"));
    }

    public void add(User user, int id) {
        model.put(id, user);
    }

    public void delete(int id) {
        model.remove(id);
    }

    public Map<Integer, User> getFromList() {
        return model;
    }
}
