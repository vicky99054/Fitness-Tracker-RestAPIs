package com.userService.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.userService.Model.UserModel;

@Repository
public interface UserRepo extends JpaRepository<UserModel, String> {

	boolean existsByEmail(String email);

	boolean existsById(String userId);

}
