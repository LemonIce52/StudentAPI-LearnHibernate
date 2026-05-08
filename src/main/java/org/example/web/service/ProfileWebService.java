package org.example.web.service;

import org.example.repository.entities.Profile;
import org.example.repository.entities.Student;
import org.example.repository.service.ProfileDBService;
import org.example.repository.service.StudentDBService;
import org.example.web.converters.ConverterDTO;
import org.example.web.dto.CreateProfileDTO;
import org.example.web.dto.ProfileDTO;
import org.example.web.dto.UpdateProfileDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProfileWebService {

    private final ProfileDBService profileDBService;
    private final StudentDBService studentDBService;
    private final ConverterDTO converterDTO;

    public ProfileWebService(ProfileDBService profileDBService, StudentDBService studentDBService, ConverterDTO converterDTO) {
        this.profileDBService = profileDBService;
        this.studentDBService = studentDBService;
        this.converterDTO = converterDTO;
    }

    public ProfileDTO getProfile(Long id) {
        if (id < 0) throw new IllegalArgumentException("id can't must be less zero!");
        return converterDTO.convertProfileToDto(profileDBService.getProfile(id));
    }

    public List<ProfileDTO> getAllProfile() {
        List<Profile> profilesDB = profileDBService.getAllProfiles();
        return profilesDB
                .stream()
                .map(converterDTO::convertProfileToDto)
                .toList();
    }

    public ProfileDTO createProfile(CreateProfileDTO createProfileDTO) {
        Student student = studentDBService.getStudent(createProfileDTO.studentId());
        Profile newProfile = new Profile(createProfileDTO.description(), LocalDateTime.now(), student);
        Profile savedProfile = profileDBService.saveProfile(newProfile);
        return converterDTO.convertProfileToDto(savedProfile);
    }

    public ProfileDTO updateProfile(UpdateProfileDTO updateProfileDTO) {
        Profile profile = profileDBService.getProfile(updateProfileDTO.id());

        if (updateProfileDTO.description() != null) {
            profile.setDescription(updateProfileDTO.description());
        }

        profile.setLastSeenProfile(LocalDateTime.now());

        Profile updateableProfile = profileDBService.updateProfile(profile);
        return converterDTO.convertProfileToDto(updateableProfile);
    }

    public void deleteProfile(Long id) {
        if (id < 0) throw new IllegalArgumentException("id can't must be less zero!");
        profileDBService.deleteProfile(id);
    }
}
