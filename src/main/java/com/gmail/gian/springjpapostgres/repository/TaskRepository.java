package com.gmail.gian.springjpapostgres.repository;

import com.gmail.gian.springjpapostgres.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, String> {
}
