package com.springland365.jpahibernatedbrecordhistory.springdataenvers.manytomany;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.history.Revision;
import org.springframework.data.history.RevisionMetadata;
import org.springframework.data.history.Revisions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class ProjectController {


    @Autowired
    EmployeeRepo  employeeRepo ;


    @Autowired
    ProjectRepo projectRepo;

    @GetMapping("/web/springdataenvers/project/{includeDeleted}")
    public String project(@PathVariable boolean includeDeleted  , Model model){


        List<ProjectEntity>  projectEntities = null;

        if(includeDeleted){
            projectEntities = this.projectRepo.selectAllNativeIncludeDeleted();
        }
        else{
            projectEntities = this.projectRepo.findAll();
        }

        List<List<ProjectRevisionDTO>>  projectRevList = findAllProjectRevisions(projectEntities);

        model.addAttribute("projectrevslist" , projectRevList);

        return "project";
    }

    public List<List<ProjectRevisionDTO>>  findAllProjectRevisions(List<ProjectEntity> projects){
        return projects.stream().map(
                projectEntity -> getRevisionHistory(projectEntity)
        ).collect(Collectors.toList());
    }
    public List<ProjectRevisionDTO>  getRevisionHistory(ProjectEntity entity){

        Revisions<Integer , ProjectEntity> revisions = projectRepo.findRevisions(entity.getId());

        List<ProjectRevisionDTO>  projectRevisionDTOList =  revisions.stream().map(rev -> toPojectRevisionDTO(rev)).collect(Collectors.toList());
        Collections.reverse(projectRevisionDTOList);
        return projectRevisionDTOList;
    }

    public List<EmployeeRevisionDTO> getRevisionHistory(EmployeeEntity entity){

        Revisions<Integer , EmployeeEntity> revisions = employeeRepo.findRevisions(entity.getId());
        return revisions.stream().map(rev -> toEmployeeRevisionDTO(rev)).collect(Collectors.toList());

    }
    public ProjectRevisionDTO toPojectRevisionDTO(Revision<Integer , ProjectEntity> revision){

        ModelMapper modelMapper = new ModelMapper();

        ProjectEntity projectEntity = revision.getEntity();
        ProjectRevisionDTO dto = modelMapper.map(projectEntity , ProjectRevisionDTO.class);
        dto.setRevisionType(revision.getMetadata().getRevisionType());
        dto.setRevisionNumber(revision.getRevisionNumber().orElse(null));

        List<EmployeeRevisionDTO> team = projectEntity.getEmployees().stream().map(
                emp -> toEmployeeRevisionDTO(emp , revision.getRevisionNumber() , revision.getMetadata().getRevisionType())
        ).collect(Collectors.toList());

        dto.setTeam(team);
        return dto ;
    }

    public EmployeeRevisionDTO toEmployeeRevisionDTO(EmployeeEntity employeeEntity , Optional<Integer> revisionNumber   , RevisionMetadata.RevisionType revisionType){
        ModelMapper mapper = new ModelMapper();
        EmployeeRevisionDTO dto =  mapper.map(employeeEntity , EmployeeRevisionDTO.class);
        dto.setRevisionNumber(revisionNumber.orElse(null));
        dto.setRevisionType(revisionType);
        return dto ;
    }

    public EmployeeRevisionDTO toEmployeeRevisionDTO(Revision<Integer , EmployeeEntity> revision){

        ModelMapper modelMapper = new ModelMapper();


        EmployeeEntity  employee = revision.getEntity();
        EmployeeRevisionDTO dto = modelMapper.map(employee , EmployeeRevisionDTO.class);

        dto.setRevisionType(revision.getMetadata().getRevisionType());
        dto.setRevisionNumber(revision.getRevisionNumber().orElse(null));

        return dto ;
    }




}
