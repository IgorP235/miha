package com.example.RestService.dao;
//This class is used for postgres implementation. To connect to another data base with changing
//just one line of code
//Dependency injection

import com.example.RestService.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository("postgres") //To change implementation all we have to do to imput this name in Server
public class CarDataAccessService implements CarDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CarDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertPerson(Person person) {
        int id = person.getId();
        String carNum = person.getCarNum();
        Boolean registered = false;

        String sql = "INSERT INTO person (" +
                "id, " +
                "carNum, " +
                "registered) " +
                "VALUES (?, ?, ?)";
        
        return jdbcTemplate.update(
                sql,
                id,
                carNum,
                registered
        );
    }

    @Override
    public List<Person> selectAllPeople() {
        final String sql = "" +
                "SELECT " +
                "id, " +
                "carNum, " +
                "registered " +
                "FROM person";
        return jdbcTemplate.query(
                sql,
                (resultSet, i) -> {
                    int id = resultSet.getInt("id");
                    String carNum = resultSet.getString("carNum");
                    Boolean registered = resultSet.getBoolean("registered");
                    return new Person(id, carNum, registered);
                });
    }

    @Override
    public Optional<Person> selectPersonById(int id) {
        final String sql = "" +
                "SELECT " +
                "id, " +
                "carNum, " +
                "registered " +
                "FROM person " +
                "WHERE id = ?";
        Person person = jdbcTemplate.queryForObject(
                sql,
                new Object[]{id},
                (resultSet, i) -> {
                    int personid = resultSet.getInt("id");
                    String carNum = resultSet.getString("carNum");
                    Boolean registered = resultSet.getBoolean("registered");
                    return new Person(personid, carNum, registered);
                });
        return Optional.ofNullable(person); //Because it can be null
    }

    @Override
    public int deletePersonById(int id) {
        String sql = "" +
                "DELETE FROM person " +
                "WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public int updateCarNumById(int id, Person person) {
        String sql = "" +
                "UPDATE person " +
                "SET carNum = ? , " +
                "registered = false " +
                "WHERE id = ?";
        String carNum = person.getCarNum();
        return jdbcTemplate.update(sql, carNum, id);
    }

    @Override
    public int updateRegisteredById(int id, Person person) {
        String sql = "" +
                "UPDATE person " +
                "SET registered = ? " +
                "WHERE id = ?";
        Boolean registered = person.getRegistered();
        return jdbcTemplate.update(sql, registered, id);
    }

    @SuppressWarnings("ConstantConditions")
    public boolean isCarNumTaken(String carNum) {
        String sql = "" +
                "SELECT EXISTS ( " +
                " SELECT 1 " +
                " FROM person " +
                " WHERE carNum = ?" +
                ")";
        return jdbcTemplate.queryForObject(
                sql,
                new Object[]{carNum},
                (resultSet, i) -> resultSet.getBoolean(1)
        );
    }

    @SuppressWarnings("ConstantConditions")
    public boolean isIdTaken(int id) {
        String sql = "" +
                "SELECT EXISTS ( " +
                " SELECT 1 " +
                " FROM person " +
                " WHERE id = ?" +
                ")";
        return jdbcTemplate.queryForObject(
                sql,
                new Object[]{id},
                (resultSet, i) -> resultSet.getBoolean(1)
        );
    }
}

