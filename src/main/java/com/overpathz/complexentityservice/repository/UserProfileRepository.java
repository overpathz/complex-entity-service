package com.overpathz.complexentityservice.repository;

import com.overpathz.complexentityservice.entities.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

}
