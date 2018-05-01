package com.gmail.gian.springjpapostgres.controller;

import com.gmail.gian.springjpapostgres.exception.ResourceNotFoundException;
import com.gmail.gian.springjpapostgres.model.Employee;
import com.gmail.gian.springjpapostgres.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by GianPH - 1/5/2018
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping({"", "list"})
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @GetMapping("{id}")
    public Employee findById(@PathVariable(value = "id") String id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
    }

    @PostMapping("create")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    //    Hard Delete
    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteEmployee(@PathVariable(value = "id") String id) {
        Employee employee = new Employee();
        employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
        employeeRepository.delete(employee);
        return ResponseEntity.ok().build();
    }

    @PutMapping("update/{id}")
    public Employee updateEmployee(@PathVariable(value = "id") String id, @RequestBody Employee newEmployee) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("employee", "id", id));
        employee.setName(newEmployee.getName());
        employee.setAge(newEmployee.getAge());
        employeeRepository.save(employee);

        return employee;
    }

}
