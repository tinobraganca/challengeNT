package com.ntconsulting.hotel.hotelservices.repository.entity;

import com.ntconsulting.hotel.hotelservices.dto.HotelDTO;
import com.ntconsulting.hotel.hotelservices.dto.RoomDTO;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "ROOM", schema = "NT_HOTEL_DB")
public class RoomEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ROOM")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PEOPLE_CAPACITY")
    private Integer peopleCapacity;

    @Column(name = "PRICE")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name="ID_HOTEL")
    private HotelEntity hotel;

    @OneToOne(mappedBy = "room")
    private BookingEntity booking;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updateAt;

    public RoomEntity(){}

    public RoomEntity(RoomDTO roomDTO, HotelEntity hotelEntity, BookingEntity bookingEntity){
        this.name = roomDTO.name();
        this.peopleCapacity = roomDTO.peopleCapacity();
        this.price = roomDTO.price();
        this.hotel = hotelEntity;
        this.booking = bookingEntity;
        this.createdAt = LocalDateTime.now();
        this.updateAt = null;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPeopleCapacity() {
        return peopleCapacity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public HotelEntity getHotel() {
        return hotel;
    }

    public BookingEntity getBooking() {
        return booking;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

}
