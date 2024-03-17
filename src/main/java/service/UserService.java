package service;

import model.UserProfile;

import java.util.HashMap;
import java.util.Map;

public class UserService {
    private static final Map<String, UserProfile> loginToProfile = new HashMap<String, UserProfile>() {{
        put("admin", new UserProfile("admin", "123", "pup"));
    }};
    private static final Map<String, UserProfile> sessionIdToProfile = new HashMap<>();

    public static void addNewUser(UserProfile userProfile) {
        loginToProfile.put(userProfile.getLogin(), userProfile);
    }

    public static UserProfile getUserByLogin(String login) {
        return loginToProfile.get(login);
    }

    public UserProfile getUserBySessionId(String sessionId) {
        return sessionIdToProfile.get(sessionId);
    }

    public void addSession(String sessionId, UserProfile userProfile) {
        sessionIdToProfile.put(sessionId, userProfile);
    }

    public void deleteSession(String sessionId) {
        sessionIdToProfile.remove(sessionId);
    }
}
