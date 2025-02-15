package com.carwash.carwash.domain.Repositories.status;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carwash.carwash.domain.Entities.status.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
    
}
