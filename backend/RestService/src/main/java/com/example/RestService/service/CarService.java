package com.example.RestService.service;

import com.example.RestService.dao.CarDao;
import com.example.RestService.exception.ApiRequestException;
import com.example.RestService.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private final CarDao carDao;

    @Autowired
    //@Qualifier("fakeDao") - Fake Database
    //@Qualifier("postgres") - Real Motherfucker Database(not working yet)
    public CarService(@Qualifier("postgres") CarDao carDao) {
        this.carDao = carDao;
    }

    public void addPerson(Person person) {
        if (carDao.isCarNumTaken(person.getCarNum())) {
            throw new ApiRequestException(
                    "Car number " + person.getCarNum() + " already is in database"
            );
        }
        if (carDao.isIdTaken(person.getId())) {
            throw new ApiRequestException(
                    "User " + person.getId() + " already exists in database"
            );
        }
        try {
            carDao.insertPerson(person);
        } catch (Exception e){
            throw new ApiRequestException(
                    "Cant add user " + person.getId() + " to database"
            );
        }
    }

    public List<Person> getAllPeople() {
        return carDao.selectAllPeople();
    }

    public Optional<Person> getPersonById(int id) {
        try {
            return carDao.selectPersonById(id);
        } catch (Exception e) {
            throw new ApiRequestException("User " + id + " not found in database");
        }
    }

    public void deletePerson(int id) {
        carDao.deletePersonById(id);
    }

    public void updatePersonCarNum(int id, Person newPerson) {
        if (carDao.isCarNumTaken(newPerson.getCarNum())) {
            throw new ApiRequestException(
                    "Car number " + newPerson.getCarNum() + " already is in database"
            );
        }
        if (!carDao.isIdTaken(id)) {
            throw new ApiRequestException(
                    "User " + id + " not found in database"
            );
        }
        carDao.updateCarNumById(id, newPerson);
    }

    public void updatePersonRegistered(int id, Person newPerson) {
        if (!carDao.isIdTaken(id)) {
            throw new ApiRequestException(
                    "User " + id + " not found in database"
            );
        }
        carDao.updateRegisteredById(id, newPerson);
    }
}
