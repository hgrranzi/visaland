package com.hgrranzi.visaland.persistence.repository;

import com.hgrranzi.visaland.persistence.entity.Applicant;
import com.hgrranzi.visaland.persistence.entity.Visa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VisaRepository extends JpaRepository<Visa, Long> {

    List<Visa> findAllByApplicantOrderByEndDate(Applicant applicant);
}
