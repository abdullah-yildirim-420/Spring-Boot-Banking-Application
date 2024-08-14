package com.banking.bank.service.impl;

import com.banking.bank.dto.*;
import com.banking.bank.entity.User;
import com.banking.bank.mapper.UserMapper;
import com.banking.bank.repository.UserRepository;
import com.banking.bank.service.UserService;
import com.banking.bank.util.UserUtil;
import com.banking.bank.util.UserValidationUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository,UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    @Transactional
    public UserResponseDTO create(UserCreateDTO userCreateDTO) {
        User user = userMapper.toEntity(userCreateDTO);
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    @Override
    public UserResponseDTO get(Long id) {
        return userRepository.findUserWithAccounts(id)
            .map(userMapper::toDto)
            .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        UserUtil.findUserByIdOrThrow(id, userRepository);
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updateUsername(Long id, UpdateUsernameDTO updateUsernameDTO) {
        User user = UserValidationUtil.validateAndUpdate(id,"username",updateUsernameDTO.getCurrentPassword(), updateUsernameDTO.getNewUsername(), userRepository);
        user.setUsername(updateUsernameDTO.getNewUsername());
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updatePassword(Long id, UpdatePasswordDTO updatePasswordDTO) {
        User user = UserValidationUtil.validateAndUpdate(id,"password",updatePasswordDTO.getCurrentPassword(), updatePasswordDTO.getNewPassword(), userRepository);
        user.setPassword(updatePasswordDTO.getNewPassword());
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateEmail(Long id, UpdateEmailDTO updateEmailDTO) {
        User user = UserValidationUtil.validateAndUpdate(id,"email",updateEmailDTO.getCurrentPassword(), updateEmailDTO.getNewEmail(), userRepository);
        user.setEmail(updateEmailDTO.getNewEmail());
        userRepository.save(user);
    }

}
