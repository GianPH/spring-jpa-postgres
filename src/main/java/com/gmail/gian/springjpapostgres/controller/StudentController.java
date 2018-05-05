package com.gmail.gian.springjpapostgres.controller;

import com.gmail.gian.springjpapostgres.model.Student;
import com.gmail.gian.springjpapostgres.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by GianPH - 5/5/2018
 */

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @GetMapping(value = {"", "list"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Student>> findAll() {
        List<Student> students = studentRepository.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(students);
    }

    @GetMapping(value = "{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> findById(@PathVariable(name = "id") String id) {
        Student student = studentRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Not Found"));
        return ResponseEntity.status(HttpStatus.OK).body(student);
    }

    @PostMapping(value = "create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Student> createStudent(@Valid @RequestBody Student student) {

        if (student == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        studentRepository.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }

    //    Hard Delete
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable(value = "id") String id) {

        Student student = studentRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Student Not Found"));

        studentRepository.delete(student);
        return ResponseEntity.status(HttpStatus.OK).body(student);
    }

    @PutMapping(value = "update",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> updateStudent(@Valid @RequestBody Student student) {
        if (student == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        studentRepository.save(student);
        return ResponseEntity.status(HttpStatus.OK).body(student);
    }
}
