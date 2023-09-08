package com.hgrranzi.visaland.persistence.repository;

import com.hgrranzi.visaland.persistence.entity.VisaCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VisaCategoryRepository extends JpaRepository<VisaCategory, Long> {

    Optional<VisaCategory> findByName(String name);
}
