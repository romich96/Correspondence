package ua.com.splan;

import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserServiceImpl implements UserService {
    public User getUser(String login) {
        User user = new User();

        HashMap<String, String> userList = new HashMap<String, String>();
        userList.put("reg", "7110eda4d09e062aa5e4a390b0a572ac0d2c0220");

        if (userList.containsKey(login)) {
            user.setLogin(login);
            user.setPassword(userList.get(login));
        }

        return user;
    }

}
