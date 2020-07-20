package com.example.RestService.dao;

import com.example.RestService.model.Person;

import java.util.List;
import java.util.Optional;

public interface CarDao {

    int insertPerson(Person person);

    List<Person> selectAllPeople();

    Optional<Person> selectPersonById(int id);

    int deletePersonById(int id);

    int updateCarNumById(int id, Person person);

    int updateRegisteredById(int id, Person person);

    boolean isCarNumTaken(String carNum);

    boolean isIdTaken(int id);

}
