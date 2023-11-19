package com.springland365.jpahibernatedbrecordhistory.springdataenvers.onetomany;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.history.Revision;
import org.springframework.data.history.Revisions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
public class PersonController {

    @Autowired
    PersonRepository  repo ;

    @GetMapping("/web/springdataenvers/persons/{includeDeleted}")
    public String findPersonRevisionHistory(@PathVariable boolean includeDeleted  , Model model ) {

        List<List<PersonRevisionDTO>> personRevision = getRevisionHistory(includeDeleted);

        model.addAttribute("personrevslist" , personRevision);
        return "person";
    }

    public List<List<PersonRevisionDTO>>  getRevisionHistory( boolean includeDeleted){

        List<PersonEntity> personEntities ;

        if(includeDeleted){
            personEntities = repo.selectAllNativeIncludeDeleted();
        }
        else{
            personEntities = repo.findAll();
        }


        return personEntities.stream().map(
                p -> getRevisionHistory(p)
        ).collect(Collectors.toList());
    }

    public List<PersonRevisionDTO>  getRevisionHistory(PersonEntity personEntity){

        Revisions<Integer , PersonEntity> revisions = repo.findRevisions(personEntity.getId());

        List<PersonRevisionDTO>  personRevisionDTOS =  revisions.stream().map( rev -> toRevisionDTO(rev)).collect(Collectors.toList());
        Collections.reverse(personRevisionDTOS);
        return personRevisionDTOS ;
    }

    public PersonRevisionDTO  toRevisionDTO(Revision<Integer , PersonEntity> personRevision){

        ModelMapper modelMapper = new ModelMapper();

        PersonRevisionDTO  personRevisionDTO = modelMapper.map(personRevision.getEntity() , PersonRevisionDTO.class);

        AddressEntity addressEntity = personRevision.getEntity().getAddress();
        if(addressEntity != null ) {
            AddressRevisionDTO addressRevisionDTO = modelMapper.map(addressEntity, AddressRevisionDTO.class);

            personRevisionDTO.setRevisionType(personRevision.getMetadata().getRevisionType());
            personRevisionDTO.setRevisionNumber(personRevision.getRevisionNumber().orElse(null));

            addressRevisionDTO.setRevisionType(personRevision.getMetadata().getRevisionType());
            addressRevisionDTO.setRevisionNumber(personRevision.getRevisionNumber().orElse(null));

            personRevisionDTO.setAddress(addressRevisionDTO);
        }
        return personRevisionDTO ;
    }
}
