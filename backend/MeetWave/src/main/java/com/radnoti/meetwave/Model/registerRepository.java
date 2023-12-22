package com.radnoti.meetwave.Model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface registerRepository extends JpaRepository<registerUserClass, Integer> {

    Optional<registerUserClass> findByFullNameIN(String fullNameIN);
}
