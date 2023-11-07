package com.mysite.sbb.lyUser;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LawyerRepository extends JpaRepository<LawyerUser, Long> {
    Optional<LawyerUser> findByusername(String username);
}