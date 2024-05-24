package com.ntconsulting.hotel.hotelservices.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

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


    public Long getId() {
        return id;
    }

    public RoomEntity getRoom() {
        return room;
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
