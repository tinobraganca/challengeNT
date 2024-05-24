package com.ntconsulting.hotel.userservice.service;

import com.ntconsulting.hotel.userservice.dto.UserDTO;
import com.ntconsulting.hotel.userservice.repository.entity.UserEntity;

import java.util.Optional;

public interface UserService {
    UserDTO save(UserDTO user);

    Optional<UserEntity> findUser(Long id);
}
