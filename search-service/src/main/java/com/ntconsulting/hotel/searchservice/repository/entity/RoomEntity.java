package com.ntconsulting.hotel.searchservice.repository.entity;

import com.ntconsulting.hotel.searchservice.dto.RoomDTO;
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="ID_HOTEL")
    private HotelEntity hotel;

    @OneToOne(mappedBy = "room")
    private BookingEntity booking;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updateAt;

    public RoomEntity(){}

    public RoomEntity(Long id, String name, Integer peopleCapacity, BigDecimal price,
                      HotelEntity hotel, BookingEntity booking){
        this.id = id;
        this.name = name;
        this.peopleCapacity = peopleCapacity;
        this.price = price;
        this.hotel = hotel;
        this.booking = booking;
        this.createdAt = LocalDateTime.now();
        this.updateAt = null;
    }

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

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPeopleCapacity(Integer peopleCapacity) {
        this.peopleCapacity = peopleCapacity;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setHotel(HotelEntity hotel) {
        this.hotel = hotel;
    }

    public void setBooking(BookingEntity booking) {
        this.booking = booking;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }
}
