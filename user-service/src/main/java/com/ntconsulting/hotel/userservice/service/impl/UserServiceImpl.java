package com.ntconsulting.hotel.userservice.service.impl;

import com.ntconsulting.hotel.userservice.dto.UserDTO;
import com.ntconsulting.hotel.userservice.repository.UserRepository;
import com.ntconsulting.hotel.userservice.repository.entity.UserEntity;
import com.ntconsulting.hotel.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService  {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO save(UserDTO user) {
        return new UserDTO(userRepository.save(new UserEntity(user)));
    }

    @Override
    public Optional<UserEntity> findUser(Long id){
        return userRepository.findById(id);
    }
}
