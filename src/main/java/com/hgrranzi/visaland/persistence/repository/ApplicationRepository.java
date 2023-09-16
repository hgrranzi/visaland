package com.hgrranzi.visaland.persistence.repository;

import com.hgrranzi.visaland.persistence.entity.AppStatus;
import com.hgrranzi.visaland.persistence.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    List<Application> findAllByApplicant_User_UsernameOrderByApplicationDate(String username);

    List<Application> findAllByStatusOrderByApplicationDate(AppStatus status);
}
