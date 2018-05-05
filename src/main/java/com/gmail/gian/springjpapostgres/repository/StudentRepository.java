package com.gmail.gian.springjpapostgres.repository;

import com.gmail.gian.springjpapostgres.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

}
