package com.carwash.carwash.domain.repositories.usuario;

import com.carwash.carwash.domain.entities.usuario.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
