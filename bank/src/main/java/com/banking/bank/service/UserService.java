package com.banking.bank.service;

import com.banking.bank.dto.*;

public interface UserService {
    UserResponseDTO create(UserCreateDTO userCreateDTO);

    UserResponseDTO get(Long id);

    void delete(Long id);

    void updateUsername(Long id, UpdateUsernameDTO updateUsernameDTO);

    void updatePassword(Long id, UpdatePasswordDTO updatePasswordDTO);

    void updateEmail(Long id, UpdateEmailDTO updateEmailDTO);
}
