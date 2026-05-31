package com.eaii.service.imp;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.eaii.exception.UserException;
import com.eaii.model.User;
import com.eaii.repository.UserRepository;
import com.eaii.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) throws UserException {
         Optional<User> otp = userRepository.findById(id);
        if (otp.isPresent()) {
            return otp.get();
        }
        throw new UserException("User not found");
    }

    @Override
    public User updateUser(Long id, User userDetails) throws UserException {
         User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new UserException("User not found");
        }

        user.setFullName(userDetails.getFullName());
        user.setEmail(userDetails.getEmail());
        user.setPhone(userDetails.getPhone());
        user.setRole(userDetails.getRole());
        user.setUserName(userDetails.getUserName());
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) throws UserException {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new UserException("User not found id:" + id);
        }
        userRepository.delete(user.get());
    }
    

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    

}
