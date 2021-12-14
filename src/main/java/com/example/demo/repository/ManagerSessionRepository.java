package com.example.demo.repository;

import com.example.demo.model.ManagerSession;
import org.springframework.data.repository.CrudRepository;

public interface ManagerSessionRepository  extends CrudRepository<ManagerSession, Long> {
    ManagerSession findBySessionHashCode(String sessionHashCode);
}
