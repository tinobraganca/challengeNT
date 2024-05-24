package com.ntconsulting.hotel.hotelservices.repository.entity;

import com.ntconsulting.hotel.hotelservices.dto.HotelDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "HOTEL_COMPANY", schema = "NT_HOTEL_DB")
public class HotelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_HOTEL")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "CITY")
    private String city;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "RATING")
    private Integer rating;

    @OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY)
    private List<RoomEntity> rooms;

    @Column(name = "MAX_ROOM")
    private Long maxRoom;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT")
    private LocalDateTime updateAt;

    public HotelEntity(){}

    public HotelEntity(HotelDTO hotelDTO){
        this.name = hotelDTO.name();
        this.country = hotelDTO.country();
        this.city = hotelDTO.city();
        this.address = hotelDTO.address();
        this.rating = hotelDTO.rating();
        this.maxRoom = hotelDTO.maxRoom();
        this.createdAt = LocalDateTime.now();
        this.updateAt = null;
    }

    public List<RoomEntity> getRooms() {
        return rooms;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public Long getMaxRoom() {
        return maxRoom;
    }

    public Integer getRating() {
        return rating;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

}
