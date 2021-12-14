package com.example.demo.model;

import javax.persistence.*;

@Entity
public class HotelManagerSession {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String sessionHashCode;
    private String ipAddress;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private HotelManager hotelManager;


    public String getSessionHashCode() {
        return sessionHashCode;
    }

    public void setSessionHashCode(String sessionHashCode) {
        this.sessionHashCode = sessionHashCode;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public HotelManager getManager() {
        return hotelManager;
    }

    public void setManager(HotelManager hotelManager) {
        this.hotelManager = hotelManager;
    }


    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }
}
