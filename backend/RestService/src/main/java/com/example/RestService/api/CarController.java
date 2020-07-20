package com.example.RestService.api;

import com.example.RestService.model.Person;
import com.example.RestService.service.CarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;

import javax.validation.Valid;
import java.util.List;


@Slf4j
@RequestMapping("api/v1/person") //@RequestMapping shortcut for class
@RestController
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addPerson(@Valid @NonNull @RequestBody Person person) {
        carService.addPerson(person);
    }

    @GetMapping //@GetMapping shortcut for method
    @ResponseStatus(HttpStatus.FOUND)
    public List<Person> getAllPeople() {
        return carService.getAllPeople();
    }

    @GetMapping(path = "{id}")
    @ResponseStatus(HttpStatus.FOUND)
    //(path = "{id}") - means that we want the actual ID in the path
    public Person getPersonById(@PathVariable("id") int id) {
        //@PathVariable used for data pass in the URL. /api/v1/person/
        return carService.getPersonById(id)
                .orElse(null); //"User not found" - here can be custom Exception

    }

    @DeleteMapping(path = "{id}")
    public void deletePersonById(@PathVariable("id") int id) {
        carService.deletePerson(id);
    }

    @PutMapping(path = "updateCar/{id}")
    public void updateCarNumById(
            @PathVariable("id") int id,
            @RequestBody Person personToUpdate) {
        carService.updatePersonCarNum(id, personToUpdate);
    }

    @PutMapping(path = "updateRegistered/{id}")
    public void updateRegisteredById(
            @PathVariable("id") int id,
            @RequestBody Person personToUpdate) {
        carService.updatePersonRegistered(id, personToUpdate);
    }


}
