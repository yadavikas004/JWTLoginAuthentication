package com.jwt.authentication.repository;

import com.jwt.authentication.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Long> {

}
