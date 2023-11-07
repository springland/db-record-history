package com.springland365.jpahibernatedbrecordhistory.springdataenvers.onetomany;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.history.Revision;
import org.springframework.data.history.Revisions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AddressController {

    @Autowired
    AddressRepository  repository ;


    @GetMapping("/web/springdataenvers/addresses/{includeDeleted}")
    public String findAllAddressRevisionHistory(@PathVariable boolean includeDeleted  , Model model){
        List<List<AddressRevisionDTO>> addressRevisionDTOList = getAddressRevisionHistory(includeDeleted) ;
        model.addAttribute("addresses" , addressRevisionDTOList);

        return "address";
    }

    @GetMapping("/api/springdataenvers/address-person")
    @ResponseBody
    public List<List<AddressRevisionDTO>>  addressPerson() {

        List<List<AddressRevisionDTO>> addressRevisionDTOList = getAddressRevisionHistory(false) ;
        return addressRevisionDTOList;

    }

    protected List<List<AddressRevisionDTO>> getAddressRevisionHistory(boolean includeDeleted) {

        List<AddressEntity> addressEntities;

        if(includeDeleted){

            addressEntities = repository.selectAllAddressNativeIncludeDeleted();
        }
        else{
                addressEntities = repository.findAll();
        }



        return addressEntities.stream().map( addr -> getAddressRevisionHistory(addr)).collect(Collectors.toList());



    }

    protected List<AddressRevisionDTO>  getAddressRevisionHistory(AddressEntity address) {
        Revisions<  Integer , AddressEntity> revisions = repository.findRevisions(address.getId());

        List<AddressRevisionDTO> addressRevisions =  revisions.stream().map( rev -> toAddressRevisionDTO(rev)).collect(Collectors.toList());
        Collections.reverse(addressRevisions);

        return addressRevisions ;

    }

    protected AddressRevisionDTO toAddressRevisionDTO(Revision<Integer , AddressEntity> revision){

        ModelMapper  modelMapper = new ModelMapper();
        AddressRevisionDTO  dto = modelMapper.map(revision.getEntity() , AddressRevisionDTO.class);

        List<PersonRevisionDTO> personRevisionDTOS = revision.getEntity().getResidents().stream().map( r -> {
            PersonRevisionDTO personRevisionDTO = modelMapper.map(r , PersonRevisionDTO.class);
            personRevisionDTO.setRevisionNumber(revision.getRevisionNumber().orElse(null));
            personRevisionDTO.setRevisionType(revision.getMetadata().getRevisionType());
            return personRevisionDTO ;
        }).collect(Collectors.toList());

        Collections.reverse(personRevisionDTOS);

        dto.setResidents(personRevisionDTOS);
        dto.setRevisionNumber(revision.getRevisionNumber().orElse(null));
        dto.setRevisionType(revision.getMetadata().getRevisionType());
        return dto ;


    }


}
