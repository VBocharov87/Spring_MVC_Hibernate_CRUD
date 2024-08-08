package web.DAO;

import web.model.User;

import java.util.List;

public interface User_DAO {

    public List<User> getAllUsers();

    void saveUpdateUser(User user);

    User getUser(int id);

    void deleteUser(int id);

}
