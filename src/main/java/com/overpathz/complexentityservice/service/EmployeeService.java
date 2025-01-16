package com.overpathz.complexentityservice.service;

import com.overpathz.complexentityservice.dto.EmployeeDTO;
import com.overpathz.complexentityservice.entities.Department;
import com.overpathz.complexentityservice.entities.Employee;
import com.overpathz.complexentityservice.entities.Project;
import com.overpathz.complexentityservice.mapper.EmployeeMapper;
import com.overpathz.complexentityservice.repository.DepartmentRepository;
import com.overpathz.complexentityservice.repository.EmployeeRepository;
import com.overpathz.complexentityservice.repository.ProjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final ProjectRepository projectRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeService(EmployeeRepository employeeRepository,
                           DepartmentRepository departmentRepository,
                           ProjectRepository projectRepository,
                           EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.projectRepository = projectRepository;
        this.employeeMapper = employeeMapper;
    }

    @Transactional
    public EmployeeDTO createEmployee(EmployeeDTO dto) {
        Employee employee = employeeMapper.toEntity(dto);

        // linking department, if provided
        if (dto.getDepartmentId() != null) {
            Department department = departmentRepository.findById(dto.getDepartmentId())
                    .orElseThrow(() -> new RuntimeException("Department not found"));
            employee.setDepartment(department);
        }

        // link projects to employee if any present
        setProjects(dto, employee);

        Employee saved = employeeRepository.save(employee);
        return employeeMapper.toDTO(saved);
    }

    @Transactional
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO dto) {
        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        existing.setName(dto.getName());
        existing.setEmail(dto.getEmail());

        if (dto.getDepartmentId() != null) {
            Department dept = departmentRepository.findById(dto.getDepartmentId())
                    .orElseThrow(() -> new RuntimeException("Department not found"));
            existing.setDepartment(dept);
        } else {
            existing.setDepartment(null);
        }

        setProjects(dto, existing);

        // user profile
        if (dto.getUserProfile() != null) {
            if (existing.getUserProfile() == null) {
                // creating
                existing.setUserProfile(
                        employeeMapper.toEntity(dto).getUserProfile()
                );
                existing.getUserProfile().setEmployee(existing);
            } else {
                // updating
                existing.getUserProfile().setAddress(dto.getUserProfile().getAddress());
                existing.getUserProfile().setPhoneNumber(dto.getUserProfile().getPhoneNumber());
                existing.getUserProfile().setEmergencyContact(dto.getUserProfile().getEmergencyContact());
            }
        } else {
            // removing
            existing.setUserProfile(null);
        }

        Employee updated = employeeRepository.save(existing);
        return employeeMapper.toDTO(updated);
    }

    private void setProjects(EmployeeDTO dto, Employee existing) {
        if (dto.getProjectIds() != null) {
            Set<Project> projectSet = new HashSet<>();
            for (Long projId : dto.getProjectIds()) {
                Project project = projectRepository.findById(projId)
                        .orElseThrow(() -> new RuntimeException("Project not found"));
                projectSet.add(project);
            }
            existing.setProjects(projectSet);
        }
    }

    @Transactional(readOnly = true)
    public EmployeeDTO getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        return employeeMapper.toDTO(employee);
    }

    @Transactional
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Set<EmployeeDTO> getAllEmployees() {
        Set<EmployeeDTO> result = new HashSet<>();
        employeeRepository.findAll().forEach(emp -> {
            result.add(employeeMapper.toDTO(emp));
        });
        return result;
    }
}
