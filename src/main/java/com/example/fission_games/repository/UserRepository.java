package com.example.fission_games.repository;

import com.example.fission_games.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findById(long id);
    User findByName(String name);
    User findByNickName(String nickName);
}
