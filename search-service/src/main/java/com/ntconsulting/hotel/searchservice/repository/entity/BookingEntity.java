package com.ntconsulting.hotel.searchservice.repository.entity;

import jakarta.persistence.*;

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

    @Column(name = "ID_USER")
    private Long userId;

    @Column(name = "ID_PAYMENT")
    private Long paymentId;

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
}
