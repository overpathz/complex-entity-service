package com.overpathz.complexentityservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDTO {
    private Long id;
    private String name;
    private String email;
    private UserProfileDTO userProfile;
    private Long departmentId; // might store just the ID for referencing? Anyway
    private Set<Long> projectIds; // or store only IDs to keep it simpler?
}
