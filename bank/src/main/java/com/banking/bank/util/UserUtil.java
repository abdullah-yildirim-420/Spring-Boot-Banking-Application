package com.banking.bank.util;

import com.banking.bank.entity.User;
import com.banking.bank.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;

public class UserUtil {

    private UserUtil() {}


    public static User findUserByIdOrThrow(Long id, UserRepository userRepository) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with ID "+id+" not found"));
    }

    public static User findAndValidate(Long id, String username,String password, UserRepository userRepository){
        User user = findUserByIdOrThrow(id,userRepository);
        UserValidationUtil.validateCurrentUsername(user.getUsername(),username);
        UserValidationUtil.validateCurrentPassword(user.getPassword(),password);
        return user;
    }

}
