package com.ntconsulting.hotel.userservice.repository.entity;


import com.ntconsulting.hotel.userservice.dto.UserDTO;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "USER", schema = "NT_HOTEL_DB")
public class UserEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USER")
    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private WalletEntity walletEntity;

    @OneToOne(mappedBy = "user")
    private BookingEntity booking;

    public UserEntity(){}

    public UserEntity(UserDTO userDTO){
        this.firstName = userDTO.firstName();
        this.lastName = userDTO.lastName();
        this.phone = userDTO.phone();
        this.email = userDTO.email();
        this.createdAt = LocalDateTime.now();
        this.walletEntity = new WalletEntity(userDTO.wallet(), this);
    }

    public BookingEntity getBooking() {
        return booking;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public WalletEntity getWalletEntity() {
        return walletEntity;
    }


}
