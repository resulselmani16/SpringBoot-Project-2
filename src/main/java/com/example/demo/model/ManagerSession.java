package com.example.demo.model;

import javax.persistence.*;

@Entity
public class ManagerSession {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String sessionHashCode;
    private String ipAddress;

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

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Manager manager;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }
}
