package com.example.service;

import com.example.dto.*;
import com.example.entity.EmailHistoryEntinty;
import com.example.entity.ProfileEntity;
import com.example.entity.SmsHistoryEntity;
import com.example.enums.ProfileRole;
import com.example.enums.ProfileStatus;
import com.example.enums.SmsStatus;
import com.example.exp.AppBadException;
import com.example.repository.EmailHistoryRepository;
import com.example.repository.ProfileRepository;
import com.example.repository.SmsHistoryRepository;
import com.example.util.JWTUtil;
import com.example.util.MDUtil;
import com.example.util.RandomUtil;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private MailSenderService mailSenderService;
    @Autowired
    private EmailHistoryRepository emailHistoryRepository;
    @Autowired
    private SmsServerServices smsServerService;
    @Autowired
    private SmsHistoryRepository smsHistoryRepository;

    public ProfileDTO auth(AuthDTO profile) {
        Optional<ProfileEntity> optional = profileRepository.findByEmailAndPassword(profile.getEmail(),
                MDUtil.encode(profile.getPassword()));

        if (optional.isEmpty()) {
            throw new AppBadException("Email or Password is wrong");
        }

        ProfileEntity entity = optional.get();

        ProfileDTO dto = new ProfileDTO();

        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setRole(entity.getRole());
        dto.setJwt(JWTUtil.encode(entity.getEmail(), entity.getRole()));
        return dto;
    }

    public Boolean registration(RegistrationDTO dto) {
        Optional<ProfileEntity> optional = profileRepository.findByEmail(dto.getEmail());
        if (optional.isPresent()) {
            if (optional.get().getStatus().equals(ProfileStatus.REGISTRATION)) {
                profileRepository.delete(optional.get());
            } else {
                throw new AppBadException("Email exists");
            }
        }
        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        entity.setPassword(MDUtil.encode(dto.getPassword()));
        entity.setStatus(ProfileStatus.REGISTRATION);
        entity.setRole(ProfileRole.ROLE_USER);
        profileRepository.save(entity);
        String jwt = JWTUtil.encodeForEmail(entity.getId());
        String text = "Hello. \n To complete registration please link to the following link\n"
                + "http://localhost:8081/auth/verification/email/" + jwt;
        mailSenderService.sendEmail(dto.getEmail(), "Complete registration", text);
        EmailHistoryEntinty entinty = new EmailHistoryEntinty();
        entinty.setEmail(dto.getEmail());
        entinty.setMassage(text);
        entinty.setCreatedDate(LocalDateTime.now());
        emailHistoryRepository.save(entinty);
        return true;
    }

    public String emailVerification(String jwt) {
        try {
            JWTDTO jwtDTO = JWTUtil.decodeForSpringSecurity(jwt);
            Optional<ProfileEntity> optional = profileRepository.findById(jwtDTO.getId());
            if (!optional.isPresent()) {
                throw new AppBadException("Profile not found");
            }
            ProfileEntity entity = optional.get();
            if (!entity.getStatus().equals(ProfileStatus.REGISTRATION)) {
                throw new AppBadException("Profile in wrong status");
            }
            profileRepository.updateStatus(entity.getId(), ProfileStatus.ACTIVE);
        } catch (JwtException e) {
            throw new AppBadException("Please tyre again.");
        }
        return null;
    }

    public Boolean registrationPone(RegistrationDTO dto) {
        Optional<ProfileEntity> optional = profileRepository.findByEmail(dto.getEmail());
        if (optional.isPresent()) {
            if (optional.get().getStatus().equals(ProfileStatus.REGISTRATION)) {
                profileRepository.delete(optional.get()); // delete
            } else {
                throw new AppBadException("Email exists");
            }
        }
        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setPassword(MDUtil.encode(dto.getPassword()));
        entity.setStatus(ProfileStatus.REGISTRATION);
        entity.setRole(ProfileRole.ROLE_USER);
        profileRepository.save(entity);

        String code = RandomUtil.getRandomSmsCode();
        String massage = "Haha";
        smsServerService.send(dto.getPhone(), massage, code);
        SmsHistoryEntity smsHistoryEntity = new SmsHistoryEntity();
        smsHistoryEntity.setStatus(SmsStatus.USED);
        smsHistoryEntity.setPhone(dto.getPhone());
        smsHistoryEntity.setMassage(massage);
        smsHistoryEntity.setCreatedDate(LocalDateTime.now());
        smsHistoryRepository.save(smsHistoryEntity);
        return true;
    }
}
