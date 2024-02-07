package com.jwt.authentication.services;

import com.jwt.authentication.entities.Course;

import java.util.List;

public interface CourseService {


    public List<Course> getCourses();

    public Course getCourse(Long courseId);

    public Course addCourse(Course course);

    public Course updateCourse(Course course);

    public void deleteCourse(Long courseId);
}
