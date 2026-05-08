package org.example.web.controllers;

import jakarta.validation.Valid;
import org.example.web.dto.CreateProfileDTO;
import org.example.web.dto.ProfileDTO;
import org.example.web.dto.UpdateProfileDTO;
import org.example.web.service.ProfileWebService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    private final Logger logger = LoggerFactory.getLogger(ProfileController.class);
    private final ProfileWebService profileWebService;

    public ProfileController(ProfileWebService profileWebService) {
        this.profileWebService = profileWebService;
    }

    @GetMapping("/{id}")
    public ProfileDTO getVoid(
            @PathVariable("id") Long id
    ) {
        logger.info("Call method get profile id={}", id);
        return profileWebService.getProfile(id);
    }

    @GetMapping
    public List<ProfileDTO> getAllProfiles() {
        logger.info("Call method get all profile");
        return profileWebService.getAllProfile();
    }

    @PostMapping("/create")
    public ProfileDTO createProfile(
            @Valid @RequestBody CreateProfileDTO createProfileDTO
    ) {
        logger.info("Call method create profile createProfileDTO={}", createProfileDTO);
        return profileWebService.createProfile(createProfileDTO);
    }

    @PutMapping("/update")
    public ProfileDTO updateProfile(
            @Valid @RequestBody UpdateProfileDTO updateProfileDTO
    ) {
        logger.info("Call method update profile updateProfileDTO={}", updateProfileDTO);
        return profileWebService.updateProfile(updateProfileDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProfile(
            @PathVariable("id") Long id
    ) {
        logger.info("Call method delete profile id={}", id);
        profileWebService.deleteProfile(id);
    }
}
