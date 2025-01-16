package com.overpathz.complexentityservice.mapper;

import com.overpathz.complexentityservice.dto.DepartmentDTO;
import com.overpathz.complexentityservice.entities.Department;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    DepartmentDTO toDTO(Department department);
    Department toEntity(DepartmentDTO departmentDTO);
}
