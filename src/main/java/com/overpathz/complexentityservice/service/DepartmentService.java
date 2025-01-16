package com.overpathz.complexentityservice.service;

import com.overpathz.complexentityservice.dto.DepartmentDTO;
import com.overpathz.complexentityservice.entities.Department;
import com.overpathz.complexentityservice.mapper.DepartmentMapper;
import com.overpathz.complexentityservice.repository.DepartmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    public DepartmentService(DepartmentRepository departmentRepository, DepartmentMapper departmentMapper) {
        this.departmentRepository = departmentRepository;
        this.departmentMapper = departmentMapper;
    }

    @Transactional
    public DepartmentDTO createDepartment(DepartmentDTO dto) {
        Department department = departmentMapper.toEntity(dto);
        Department saved = departmentRepository.save(department);
        return departmentMapper.toDTO(saved);
    }

    @Transactional(readOnly = true)
    public DepartmentDTO getById(Long id) {
        Department dept = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
        return departmentMapper.toDTO(dept);
    }

    @Transactional
    public DepartmentDTO updateDepartment(Long id, DepartmentDTO dto) {
        Department existing = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
        existing.setName(dto.getName());
        Department updated = departmentRepository.save(existing);
        return departmentMapper.toDTO(updated);
    }

    @Transactional
    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public int getEmployeeCount(Long deptId) {
        return departmentRepository.countEmployeesInDepartment(deptId);
    }

    @Transactional(readOnly = true)
    public List<DepartmentDTO> getAllDepartments() {
        return departmentRepository.findAll().stream().map(departmentMapper::toDTO).toList();
    }
}
