package com.example.fission_games.service;

import com.example.fission_games.dto.UserDto;
import com.example.fission_games.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findByEmail(String email);
    User findByNickName(String nickName);
    List<UserDto> findAllUsers();
    List<User> findAll();
    User findById(long id);
    User save(User usuario);
    void delete(User usuario);
    void deleteById(long id);
}
