package com.overpathz.complexentityservice.mapper;

import com.overpathz.complexentityservice.dto.ProjectDTO;
import com.overpathz.complexentityservice.entities.Project;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    ProjectDTO toDTO(Project project);
    Project toEntity(ProjectDTO projectDTO);
}
