package com.gmail.gian.springjpapostgres.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.gmail.gian.springjpapostgres.exception.ResourceNotFoundException;
import com.gmail.gian.springjpapostgres.model.Employee;
import com.gmail.gian.springjpapostgres.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by GianPH - 1/5/2018
 */
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ObjectMapper objectMapper;

    @GetMapping(value = {"", "list"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ObjectNode> findAll() {

        List<Employee> employees = employeeRepository.findAll();
        ObjectNode objectNode = objectMapper.createObjectNode();
        ArrayNode arrayNode = objectMapper.createArrayNode();
        objectNode.putObject("data");
        employees.forEach(employee -> {
                    arrayNode.add(objectMapper.convertValue(employee, ObjectNode.class));
                }
        );

        objectNode.set("data", arrayNode);

        return ResponseEntity.status(HttpStatus.OK).body(objectNode);
    }

    @GetMapping(value = "{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ObjectNode> findById(@PathVariable(value = "id") String id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id)
                );
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.putObject("data");
        objectNode.set("data", objectMapper.convertValue(employee, ObjectNode.class));

        return ResponseEntity.status(HttpStatus.OK).body(objectNode);
    }

    @PostMapping(value = "create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ObjectNode> createEmployee(@Valid @RequestBody Employee employee) {

        employeeRepository.save(employee);
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.putObject("data");
        objectNode.set("data", objectMapper.convertValue(employee, ObjectNode.class));

        return ResponseEntity.status(HttpStatus.CREATED).body(objectNode);
    }

    //    Hard Delete
    @DeleteMapping("delete/{id}")
    public ResponseEntity<ObjectNode> deleteEmployee(@PathVariable(value = "id") String id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
        employeeRepository.delete(employee);
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.putObject("data");
        objectNode.set("data", objectMapper.convertValue(employee, ObjectNode.class));
        return ResponseEntity.status(HttpStatus.OK).body(objectNode);
    }

    @PutMapping(value = "update/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee updateEmployee(@PathVariable(value = "id") String id,
                                   @Valid @RequestBody Employee newEmployee) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("employee", "id", id));
        employee.setName(newEmployee.getName());
        employee.setAge(newEmployee.getAge());
        employeeRepository.save(employee);

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.putObject("data");
        objectNode.set("data", objectMapper.convertValue(employee, ObjectNode.class));

        return employee;
    }

}
