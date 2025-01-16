package com.overpathz.complexentityservice.mapper;

import com.overpathz.complexentityservice.dto.EmployeeDTO;
import com.overpathz.complexentityservice.entities.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {
        UserProfileMapper.class,
        DepartmentMapper.class,
        ProjectMapper.class
})
public abstract class EmployeeMapper {

    private final UserProfileMapper userProfileMapper;

    public EmployeeMapper(UserProfileMapper userProfileMapper) {
        this.userProfileMapper = userProfileMapper;
    }

    public abstract EmployeeDTO toDTO(Employee employee);

    @Mapping(target = "userProfile", expression = "java(userProfileMapper.toEntity(employeeDTO.getUserProfile()))")
    @Mapping(target = "projects", ignore = true) // we will handle project IDs manually
    @Mapping(target = "department", ignore = true) // we will handle department IDs manually
    public abstract Employee toEntity(EmployeeDTO employeeDTO);
}
