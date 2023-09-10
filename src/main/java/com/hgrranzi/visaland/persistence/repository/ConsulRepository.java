package com.hgrranzi.visaland.persistence.repository;

import com.hgrranzi.visaland.persistence.entity.Consul;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConsulRepository extends JpaRepository<Consul, Long> {

    Optional<Consul> findByUserUsername(String username);
}
