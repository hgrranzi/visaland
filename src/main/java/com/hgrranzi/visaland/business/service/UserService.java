package com.hgrranzi.visaland.business.service;

import com.hgrranzi.visaland.api.dto.ApplicantDto;
import com.hgrranzi.visaland.persistence.entity.Applicant;
import com.hgrranzi.visaland.persistence.entity.RoleName;
import com.hgrranzi.visaland.persistence.entity.User;
import com.hgrranzi.visaland.persistence.repository.ApplicantRepository;
import com.hgrranzi.visaland.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ApplicantRepository applicantRepository;
    private final PasswordEncoder passwordEncoder;

    public void registerUserAsApplicant(ApplicantDto applicantDto) {
        User user = new User(applicantDto.getUsername(),
                             applicantDto.getEmail(),
                             passwordEncoder.encode(applicantDto.getPassword()));
        user.setRole(RoleName.ROLE_APPLICANT);

        Applicant applicant = Applicant.builder()
                                  .firstName(applicantDto.getFirstName())
                                  .lastName(applicantDto.getLastName())
                                  .dateOfBirth(applicantDto.getDateOfBirth())
                                  .passportNumber(applicantDto.getPassportNumber())
                                  .phone(applicantDto.getPhone())
                                  .profession(applicantDto.getProfession())
                                  .build();
        applicant.setUser(user);

        userRepository.save(user);
        applicantRepository.save(applicant);
    }

    public void updateApplicantProfile(ApplicantDto updateApplicantDto) {
        Applicant applicant = applicantRepository.findByUserUsername(updateApplicantDto.getUsername())
                                  .orElseThrow(() -> new RuntimeException("Кастом эесепшен"));

        applicant.setProfession(updateApplicantDto.getProfession());

        applicantRepository.save(applicant);
    }

    public ApplicantDto createApplicantDtoByUsername(String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        Applicant applicant = applicantRepository.findByUserUsername(username).orElse(null);
        if (null == user || null == applicant) {
            throw new RuntimeException("Кастом эесепшен");
        }
        return ApplicantDto.builder()
                   .username(username)
                   .email(user.getEmail())
                   .firstName(applicant.getFirstName())
                   .lastName(applicant.getLastName())
                   .dateOfBirth(applicant.getDateOfBirth())
                   .passportNumber(applicant.getPassportNumber())
                   .phone(applicant.getPhone())
                   .profession(applicant.getProfession())
                   .build();
    }

    public boolean existsByUniqueData(ApplicantDto registrationDto, BindingResult result) {
        boolean returnValue = false;
        if (userRepository.existsByUsername(registrationDto.getUsername())) {
            result.rejectValue("username", "error.username.exists");
            returnValue = true;
        }
        if (userRepository.existsByEmail(registrationDto.getEmail())) {
            result.rejectValue("email", "error.email.exists");
            returnValue = true;
        }
        if (applicantRepository.existsByPassportNumber(registrationDto.getPassportNumber())) {
            result.rejectValue("passportNumber", "error.passport.exists");
            returnValue = true;
        }
        if (applicantRepository.existsByPhone(registrationDto.getPassportNumber())) {
            result.rejectValue("phone", "error.phone.exists");
            returnValue = true;
        }
        return returnValue;
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

}
