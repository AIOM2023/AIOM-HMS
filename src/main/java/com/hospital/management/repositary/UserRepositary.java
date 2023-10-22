package com.hospital.management.repositary;

import com.hospital.management.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositary extends JpaRepository<User,String> {

}
