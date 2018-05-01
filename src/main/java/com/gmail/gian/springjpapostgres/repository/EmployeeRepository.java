package com.gmail.gian.springjpapostgres.repository;

import com.gmail.gian.springjpapostgres.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by GianPH - 1/5/2018
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee,String> {

}
