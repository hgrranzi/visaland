package com.hgrranzi.visaland.persistence.repository;

import com.hgrranzi.visaland.persistence.entity.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicantRepository extends JpaRepository<Applicant, Long> {

    Optional<Applicant> findByUserUsername(String username);

    boolean existsByPassportNumber(String passportNumber);

}
