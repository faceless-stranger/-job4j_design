package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        int remove = 0;
        HashMap<Integer, User> check = new HashMap<>();
        for (User prev : previous) {
            check.put(prev.getId(), prev);
        }
        if (previous.size() != current.size() || !previous.containsAll(current)) {
            for (User cur : current) {
                if (!check.containsKey(cur.getId())) {
                    added++;
                }
                if (check.containsKey(cur.getId()) && !check.containsValue(cur)) {
                    changed++;
                }
                check.remove(cur.getId());
            }
            remove = check.size();
        }
        return new Info(added, changed, remove);
    }
}
