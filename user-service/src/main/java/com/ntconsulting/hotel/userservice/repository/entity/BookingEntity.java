package com.ntconsulting.hotel.userservice.repository.entity;

import com.ntconsulting.hotel.userservice.dto.RequestBookingDTO;
import com.ntconsulting.hotel.userservice.enumaration.BookingStatusEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "BOOKING", schema = "NT_HOTEL_DB")
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_BOOKING")
    private Long id;

    @OneToOne
    @JoinColumn(name = "ID_ROOM")
    private RoomEntity room;

    @OneToOne
    @JoinColumn(name = "ID_USER")
    private UserEntity user;

    @OneToOne
    @JoinColumn(name="ID_PAYMENT")
    private PaymentEntity payment;

    @Column(name = "START_AT")
    private LocalDateTime startAt;

    @Column(name = "END_AT")
    private LocalDateTime endAt;

    @Column(name = "NAME")
    private String name;

    @Column(name = "COUNT_PEOPLE")
    private Integer countPeople;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updateAt;

    public BookingEntity(){}

    public BookingEntity(RequestBookingDTO requestBooking, RoomEntity room, UserEntity user, PaymentEntity payment) {
        this.room = room;
        this.user = user;
        this.payment = payment;
        this.name = requestBooking.name();
        this.startAt = LocalDateTime.parse(requestBooking.startAt(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.endAt = LocalDateTime.parse(requestBooking.startAt(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.countPeople = requestBooking.countPeople();
        this.status = BookingStatusEnum.BOOKED.getMessage();
        this.createdAt = LocalDateTime.now();
        this.updateAt = null;

    }

    public Long getId() {
        return id;
    }

    public RoomEntity getRoom() {
        return room;
    }

    public UserEntity getUser() {
        return user;
    }

    public PaymentEntity getPayment() {
        return payment;
    }

    public LocalDateTime getStartAt() {
        return startAt;
    }

    public LocalDateTime getEndAt() {
        return endAt;
    }

    public String getName() {
        return name;
    }

    public Integer getCountPeople() {
        return countPeople;
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
}
