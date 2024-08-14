package com.banking.bank.mapper;

import com.banking.bank.dto.UserCreateDTO;
import com.banking.bank.dto.UserResponseDTO;
import com.banking.bank.entity.User;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public User toEntity(UserCreateDTO dto) {
        return new User(
                dto.getFirstName(),
                dto.getLastName(),
                dto.getDateOfBirth(),
                dto.getUsername(),
                dto.getPassword(),
                dto.getEmail(),
                dto.getPhone()
        );
    }

    public UserResponseDTO toDto(User user) {
        List<Long> accountIds = user.getAccounts() != null ?
                user.getAccounts().stream()
                        .map(account -> account.getId())
                        .collect(Collectors.toList()) :
                List.of();

        return new UserResponseDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getDateOfBirth(),
                user.getUsername(),
                user.getEmail(),
                user.getPhone(),
                user.getCreatedAt(),
                user.getUpdatedAt(),
                accountIds
        );

    }
}

