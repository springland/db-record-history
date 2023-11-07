package com.springland365.jpahibernatedbrecordhistory.springdataenvers.onetomany;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Component
public class AddressLoader implements ApplicationRunner {

    @Autowired
    AddressRepository addressRepository ;

    @Autowired
    PersonRepository personRepository ;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        addResidence();
        removeResidence() ;
        changeAddress();
        deleteAddress();
    }

    protected void addResidence() throws Exception {
        AddressEntity address = new AddressEntity();
        address.setCity("New York");
        address.setState("NY");
        address.setZipCode("10172");
        address.setStreet("277 Park Ave");

        PersonEntity john = new PersonEntity("John" , "Doe" , address);

        List<PersonEntity> residences = List.of(
                john
        );
        address.setResidents(residences);
        address = addressRepository.save(address);

        residences = new ArrayList<>();
        PersonEntity jane = new PersonEntity("Jane" , "Doe" , address);
        residences.addAll(address.getResidents());
        residences.add(jane);
        address.setResidents(residences);

        address.setDesc("Add residence");
        address = addressRepository.save(address);

        addressRepository.flush();


        // printAddressHistory(address.getId());
    }


    protected void removeResidence() throws InterruptedException {

        AddressEntity address = new AddressEntity();
        address.setCity("New York");
        address.setState("NY");
        address.setZipCode("10172");
        address.setStreet("270 Park Ave");
        address.setDesc("Remove residence");
        PersonEntity john = new PersonEntity("John" , "Smith" , address);
        PersonEntity jane = new PersonEntity("Jane" , "Smith" , address);

        List<PersonEntity> residences = List.of(
                john , jane
        );
        address.setResidents(residences);
        address = addressRepository.save(address);

        Thread.sleep(500);

        jane.setAddress(null);
        personRepository.save(jane);


    }

    protected void changeAddress() throws Exception {

        AddressEntity park299 = new AddressEntity();
        park299.setCity("New York");
        park299.setState("NY");
        park299.setZipCode("10171");
        park299.setStreet("299 Park Ave");
        park299.setDesc("Change address");

        AddressEntity  park280 = new AddressEntity();
        park280.setCity("New York");
        park280.setState("NY");
        park280.setZipCode("10017");
        park280.setStreet("280 Park Ave");
        park280.setDesc("Change address");


        PersonEntity  james = new PersonEntity();
        james.setFirstName("James");
        james.setLastName("Lee");
        james.setAddress(park299);
        park299.setResidents(List.of(james));


        PersonEntity  patrick = new PersonEntity();
        patrick.setFirstName("Patrick");
        patrick.setLastName("Brown");
        patrick.setAddress(park299);
        park299.setResidents(List.of(patrick, james));

        addressRepository.save(park299);
        addressRepository.save(park280);

        Thread.sleep(500);

        // move james to 280

        List<PersonEntity> residents = new ArrayList<>();
        residents.addAll(park299.residents);
        residents.remove(james);
        park299.setResidents(residents);
        park280.setResidents(List.of(james));
        james.setAddress(park280);
        addressRepository.save(park299);
        addressRepository.save(park280);


    }

    protected void deleteAddress() throws Exception {

        AddressEntity park301 = new AddressEntity();
        park301.setCity("New York");
        park301.setState("NY");
        park301.setZipCode("10022");
        park301.setStreet("301 Park Ave");
        park301.setDesc("Delete address");

        PersonEntity jimmy = new PersonEntity();
        jimmy.setFirstName("Jimmy");
        jimmy.setLastName("Davis");

        jimmy.setAddress(park301);
        park301.setResidents(List.of(jimmy));

        addressRepository.save(park301);

        Thread.sleep(500);

        addressRepository.delete(park301);
    }
}
