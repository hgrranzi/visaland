package com.hgrranzi.visaland.business.service;

import com.hgrranzi.visaland.api.dto.ApplicantDto;
import com.hgrranzi.visaland.business.exception.VisalandException;
import com.hgrranzi.visaland.business.mapper.ApplicantMapper;
import com.hgrranzi.visaland.persistence.entity.Applicant;
import com.hgrranzi.visaland.persistence.entity.RoleName;
import com.hgrranzi.visaland.persistence.entity.User;
import com.hgrranzi.visaland.persistence.repository.ApplicantRepository;
import com.hgrranzi.visaland.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final ApplicantRepository applicantRepository;

    private final PasswordEncoder passwordEncoder;

    private final ApplicantMapper applicantMapper;

    public void registerUserAsApplicant(ApplicantDto applicantDto) {
        User user = new User(applicantDto.getUsername(),
                             applicantDto.getEmail(),
                             passwordEncoder.encode(applicantDto.getPassword()));
        user.setRole(RoleName.ROLE_APPLICANT);
        Applicant applicant = applicantMapper.dtoToEntity(applicantDto, user);

        userRepository.save(user);
        applicantRepository.save(applicant);
    }

    public void updateApplicantProfileForUserWithUsername(ApplicantDto applicantDto, String username) {
        Applicant applicant = findApplicantByUsername(username);

        applicant.setFirstName(applicantDto.getFirstName());
        applicant.setFirstName(applicantDto.getLastName());
        applicant.setProfession(applicantDto.getProfession());

        applicantRepository.save(applicant);
    }

    public ApplicantDto createApplicantDtoByUsername(String username) {
        Applicant applicant = findApplicantByUsername(username);

        return applicantMapper.entityToDto(applicant);
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

    private Applicant findApplicantByUsername(String username) {
        return applicantRepository.findByUserUsername(username).orElseThrow(
            () -> new VisalandException(BAD_REQUEST, format("User not found with username %s", username)));
    }

}
