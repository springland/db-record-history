package com.springland365.jpahibernatedbrecordhistory.springdataenvers.onetomany;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.history.Revision;
import org.springframework.data.history.Revisions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class AddressPersonController {

    @Autowired
    AddressRepository  repository ;


    @GetMapping("/web/springdataenvers/address-person")
    public String addressPerson(Model model){
        List<AddressRevisionDTO> addressRevisionDTOList = getAddressRevisionHistory() ;
        model.addAttribute("addresses" , addressRevisionDTOList);

        return "address-person";
    }

    @GetMapping("/api/springdataenvers/address-person")
    @ResponseBody
    public List<AddressRevisionDTO>  addressPerson() {

        List<AddressRevisionDTO> addressRevisionDTOList = getAddressRevisionHistory() ;
        return addressRevisionDTOList;

    }

    protected List<AddressRevisionDTO> getAddressRevisionHistory() {

        List<AddressEntity> addressEntities = repository.findAll();


        return addressEntities.stream().flatMap( addr -> getAddressRevisionHistory(addr).stream()).collect(Collectors.toList());



    }

    protected List<AddressRevisionDTO>  getAddressRevisionHistory(AddressEntity address) {
        Revisions<  Integer , AddressEntity> revisions = repository.findRevisions(address.getId());

        return revisions.stream().map( rev -> toAddressRevisionDTO(rev)).collect(Collectors.toList());

    }

    protected AddressRevisionDTO toAddressRevisionDTO(Revision<Integer , AddressEntity> revision){

        ModelMapper  modelMapper = new ModelMapper();
        AddressRevisionDTO  dto = modelMapper.map(revision.getEntity() , AddressRevisionDTO.class);

        List<PersonRevisionDTO> personRevisionDTOS = revision.getEntity().getResidents().stream().map( r -> modelMapper.map(r , PersonRevisionDTO.class)).collect(Collectors.toList());
        dto.setResidents(personRevisionDTOS);
        return dto ;


    }


}
