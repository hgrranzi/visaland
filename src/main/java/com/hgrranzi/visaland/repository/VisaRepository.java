package com.hgrranzi.visaland.repository;

import com.hgrranzi.visaland.entity.Applicant;
import com.hgrranzi.visaland.entity.Visa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VisaRepository extends JpaRepository<Visa, Long> {

    List<Visa> findAllByApplicantOrderByEndDate(Applicant applicant);
}
