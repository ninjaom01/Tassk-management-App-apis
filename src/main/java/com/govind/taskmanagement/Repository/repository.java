package com.govind.taskmanagement.Repository;

import com.govind.taskmanagement.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface repository extends JpaRepository<Task, Integer> {

}
