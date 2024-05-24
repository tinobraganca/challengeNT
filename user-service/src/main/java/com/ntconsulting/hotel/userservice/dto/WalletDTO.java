package com.ntconsulting.hotel.userservice.dto;

import com.ntconsulting.hotel.userservice.repository.entity.WalletEntity;

public record WalletDTO(Long id, String name, String type, String code) {

    public WalletDTO(WalletEntity wallet){
        this(wallet.getId(), wallet.getName(), wallet.getType(), wallet.getCode());
    }
}
