package com.ntconsulting.hotel.userservice.repository.entity;

import com.ntconsulting.hotel.userservice.dto.WalletDTO;
import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "WALLET", schema = "NT_HOTEL_DB")
public class WalletEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_WALLET")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "CODE")
    private String code;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updateAt;

    @OneToOne
    @JoinColumn(name="ID_USER")
    private UserEntity user;

    public WalletEntity(){}

    public WalletEntity(WalletDTO wallet, UserEntity user) {
        this.name = wallet.name();
        this.type = wallet.type();
        this.code = wallet.code();
        this.createdAt = LocalDateTime.now();
        this.updateAt = null;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getCode() {
        return code;
    }

    public UserEntity getUser() {
        return user;
    }

}
