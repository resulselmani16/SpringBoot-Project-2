package com.example.demo.repository;

import com.example.demo.model.HotelManagerSession;
import org.springframework.data.repository.CrudRepository;

public interface HotelManagerSessionRepository extends CrudRepository<HotelManagerSession, Long> {
    HotelManagerSession findBySessionHashCode(String sessionHashCode);
}
