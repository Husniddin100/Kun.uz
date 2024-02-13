package com.example.controller;

import com.example.dto.JWTDTO;
import com.example.dto.ProfileDTO;
import com.example.dto.ProfileFilterDTO;
import com.example.enums.ProfileRole;
import com.example.service.ProfileService;
import com.example.util.HttpRequestUtil;
import com.example.util.JWTUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@Tag(name = "Profile Api list", description = "Api list for Profile")
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @Operation(summary = "Api for Create Profile", description = "this api used for creating profile")
    @PostMapping("/adm")
    public ResponseEntity<ProfileDTO> create(@RequestBody ProfileDTO dto,
                                             HttpServletRequest request) {
        log.info("Created profile {}{}", dto.getEmail(), dto.getName());
        HttpRequestUtil.getJWTDTO(request, ProfileRole.ROLE_ADMIN);
        return ResponseEntity.ok(profileService.create(dto));
    }

    @Operation(summary = "Api for update", description = "this api used for updated profile")
    @PutMapping("/adm/updateAdmin/{id}")
    public ResponseEntity<Boolean> update(@PathVariable Integer id, @RequestBody ProfileDTO dto,
                                          HttpServletRequest request) {
        HttpRequestUtil.getJWTDTO(request, ProfileRole.ROLE_ADMIN, ProfileRole.ROLE_MODERATOR);
        return ResponseEntity.ok(profileService.updateAdmin(id, dto));
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity<Boolean> update(@PathVariable Integer id, @RequestBody ProfileDTO dto) {
        return ResponseEntity.ok(profileService.updateUser(id, dto));
    }

    @GetMapping("/adm/all")
    public ResponseEntity<PageImpl> getAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                           @RequestParam(value = "size", defaultValue = "10") Integer size,
                                           HttpServletRequest request) {
        HttpRequestUtil.getJWTDTO(request, ProfileRole.ROLE_ADMIN, ProfileRole.ROLE_MODERATOR);
        return ResponseEntity.ok(profileService.getAll(page, size));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Integer id) {
        log.info("delete profile{}", id);
        return ResponseEntity.ok(profileService.delete(id));
    }

    @Operation(summary = "Api for profile", description = "this api used for get all pagination list profile")
    @PostMapping("/filter")
    public ResponseEntity<PageImpl<ProfileDTO>> filter(@RequestBody ProfileFilterDTO dto,
                                                       @RequestParam(value = "page", defaultValue = "1") int page,
                                                       @RequestParam(value = "size", defaultValue = "10") int size) {
        PageImpl<ProfileDTO> result = profileService.filter(dto, page, size);
        return ResponseEntity.ok(result);
    }

}
