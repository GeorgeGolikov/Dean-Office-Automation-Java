package sample.service.impl;

import sample.database.dao.UserDao;
import sample.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao dao;

    public UserServiceImpl(UserDao userDao) {
        this.dao = userDao;
    }

    @Override
    public boolean userExists(String login, String password) {
        return dao.selectUserFromDb(login, password);
    }
}
