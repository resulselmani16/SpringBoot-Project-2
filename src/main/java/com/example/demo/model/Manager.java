package com.example.demo.model;


import javax.persistence.*;
import java.util.List;

@Entity
public class Manager {
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //perveq id's i ka edhe qato attributes qe ka me i marre prej inputeve

    private String name;
    private String lastName;
    private String email;
    private String password;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private List<ManagerSession> managerSessionList;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }
}
