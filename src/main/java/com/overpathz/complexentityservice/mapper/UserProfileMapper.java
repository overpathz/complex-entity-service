package com.overpathz.complexentityservice.mapper;

import com.overpathz.complexentityservice.dto.UserProfileDTO;
import com.overpathz.complexentityservice.entities.UserProfile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {

    UserProfileDTO toDTO(UserProfile userProfile);

    UserProfile toEntity(UserProfileDTO userProfileDTO);

}
