package com.hgrranzi.visaland.repository;

import com.hgrranzi.visaland.entity.Applicant;
import com.hgrranzi.visaland.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    List<Application> findAllByApplicantOrderByApplicationDate(Applicant applicant);
}
