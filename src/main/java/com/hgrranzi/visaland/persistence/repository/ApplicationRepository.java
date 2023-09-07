package com.hgrranzi.visaland.persistence.repository;

import com.hgrranzi.visaland.persistence.entity.Applicant;
import com.hgrranzi.visaland.persistence.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    List<Application> findAllByApplicantOrderByApplicationDate(Applicant applicant);
}
