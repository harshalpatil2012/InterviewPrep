package test.activity;

import com.test.User;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.TreeMap;

public class ActivityTracker {

    Map<Integer, String> activeUserMap = new TreeMap<>();
    LinkedHashSet<String> hashSet = new LinkedHashSet<>(); // all users

    // List of users - usokokerId
    public void processEvent(UserActivity userActivity) {
       user =  activeUserMap.get(userActivity);
        user.getTimeStamp() < userActivity.getActivity()
                then add it in activeUserMap

        activeUserMap.put(userActivity.getTimeStamp(), userActivity.getUserId());
        hashSet.addAll(activeUserMap.keySet());
    }

    public static void main(String[] args) {

    }
//(user1, 5, “Like”)
//(user2, 1, “Like”)
//(user3, 4, “Comment”)
//(user2, 2, “Like”)
//(user1, 3, “Like”)
//find last 2 users()

}