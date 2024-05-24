package com.ntconsulting.hotel.userservice.dto;


import com.ntconsulting.hotel.userservice.repository.entity.UserEntity;

public record UserDTO(Long id, String firstName, String lastName, String phone, String email, WalletDTO wallet) {

    public UserDTO(UserEntity user){
        this(user.getId(), user.getFirstName(), user.getLastName(), user.getPhone(), user.getEmail(), new WalletDTO(user.getWalletEntity()));
    }
}
