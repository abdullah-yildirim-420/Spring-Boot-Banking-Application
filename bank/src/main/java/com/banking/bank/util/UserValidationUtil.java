package com.banking.bank.util;

import com.banking.bank.entity.User;
import com.banking.bank.exception.InvalidPasswordException;
import com.banking.bank.exception.InvalidUsernameException;
import com.banking.bank.exception.SameValueException;
import com.banking.bank.repository.UserRepository;

public final class UserValidationUtil {

    private UserValidationUtil() {}

    public static void validateCurrentPassword(String currentPassword, String validationPassword) {
        if (!currentPassword.equals(validationPassword)) {
            throw new InvalidPasswordException("Current password is incorrect");
        }
    }

    public static void validateCurrentUsername(String currentUsername, String validationUsername) {
        if (!currentUsername.equals(validationUsername)) {
            throw new InvalidUsernameException("Current username is incorrect");
        }
    }

    public static void validateConflict(String attribute, String newest, String oldest) {
        if (newest.equals(oldest)) {
            throw new SameValueException("The new " + attribute + " is the same as the current " + attribute + ". No update made.");
        }
    }

    public static User validateAndUpdate(Long id, String attribute, String password, String newOne, UserRepository userRepository) {
        User user = UserUtil.findUserByIdOrThrow(id, userRepository);
        validateCurrentPassword(user.getPassword(), password);
        switch (attribute) {
            case "username":
                validateConflict(attribute, newOne, user.getUsername());
                break;
            case "password":
                validateConflict(attribute, newOne, user.getPassword());
                break;
            case "email":
                validateConflict(attribute, newOne, user.getEmail());
                break;
            default:
                throw new IllegalArgumentException("Invalid attribute: " + attribute);
        }
        return user;
    }
}
