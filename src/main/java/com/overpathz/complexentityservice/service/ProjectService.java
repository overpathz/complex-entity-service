package com.overpathz.complexentityservice.service;

import com.overpathz.complexentityservice.dto.ProjectDTO;
import com.overpathz.complexentityservice.entities.Project;
import com.overpathz.complexentityservice.mapper.ProjectMapper;
import com.overpathz.complexentityservice.repository.ProjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    public ProjectService(ProjectRepository projectRepository, ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
    }

    @Transactional
    public ProjectDTO createProject(ProjectDTO dto) {
        Project project = projectMapper.toEntity(dto);
        Project saved = projectRepository.save(project);
        return projectMapper.toDTO(saved);
    }

    @Transactional(readOnly = true)
    public ProjectDTO getById(Long id) {
        Project proj = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        return projectMapper.toDTO(proj);
    }

    @Transactional
    public ProjectDTO updateProject(Long id, ProjectDTO dto) {
        Project existing = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        existing.setName(dto.getName());
        Project updated = projectRepository.save(existing);
        return projectMapper.toDTO(updated);
    }

    @Transactional
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    // pagination?
    @Transactional(readOnly = true)
    public Set<ProjectDTO> getAllProjects() {
        return projectRepository.findAll()
                .stream()
                .map(projectMapper::toDTO)
                .collect(Collectors.toSet());
    }
}
