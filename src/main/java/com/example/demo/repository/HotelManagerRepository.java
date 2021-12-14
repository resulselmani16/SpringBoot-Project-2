package com.example.demo.repository;

import com.example.demo.model.HotelManager;
import org.springframework.data.repository.CrudRepository;

public interface HotelManagerRepository extends CrudRepository<HotelManager, Long>{

    HotelManager findByEmail(String email);
    HotelManager findByEmailAndPassword(String email, String password);

}
