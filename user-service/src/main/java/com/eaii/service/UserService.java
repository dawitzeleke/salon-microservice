package com.eaii.service;
import java.util.List;

import com.eaii.exception.UserException;
import com.eaii.model.User;

public interface UserService {

    User createUser(User user);
    User getUserById(Long id) throws UserException;
    User updateUser(Long id, User userDetails) throws UserException;
    void deleteUser(Long id) throws UserException;
    List<User> getAllUsers();
}
