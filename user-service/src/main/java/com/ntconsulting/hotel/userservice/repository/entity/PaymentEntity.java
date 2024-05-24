package com.ntconsulting.hotel.userservice.repository.entity;

import com.ntconsulting.hotel.userservice.enumaration.PaymentStatusEnum;
import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "PAYMENT", schema = "NT_HOTEL_DB")
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PAYMENT")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updateAt;

    @OneToOne(mappedBy = "payment")
    private BookingEntity booking;

    public PaymentEntity() {
    }

    public PaymentEntity(String name, String type, PaymentStatusEnum paymentStatusEnum) {
        this.name = name;
        this.type = type;
        this.status = paymentStatusEnum.getMessage();
        this.createdAt = LocalDateTime.now();
        this.updateAt = null;
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

    public String getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public BookingEntity getBooking() {
        return booking;
    }
}
