package com.banking.bank.repository;

import com.banking.bank.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
        @Query("SELECT u FROM User u LEFT JOIN FETCH u.accounts WHERE u.id = :userId")
        Optional<User> findUserWithAccounts(@Param("userId") Long userId);

}
