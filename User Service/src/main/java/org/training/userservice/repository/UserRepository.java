package org.training.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.training.userservice.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findUserByEmailIdOrContactNo(String emailId, String contactNo);
}
