package com.byt.freeEdu.repository;

import com.byt.freeEdu.model.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
}
