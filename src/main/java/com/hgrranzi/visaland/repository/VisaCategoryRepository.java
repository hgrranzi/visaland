package com.hgrranzi.visaland.repository;

import com.hgrranzi.visaland.entity.VisaCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisaCategoryRepository extends JpaRepository<VisaCategory, Long> {

    VisaCategory findByName(String name);
}
