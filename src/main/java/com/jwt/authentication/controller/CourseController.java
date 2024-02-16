package com.jwt.authentication.controller;

import com.jwt.authentication.entities.Course;
import com.jwt.authentication.services.CourseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasRole('USER')")
public class CourseController {
    private Logger logger = LogManager.getLogger(CourseController.class);

    @Autowired
    private CourseService courseService;

    // get the all courses
    @GetMapping("/courses")
    public List<Course> getCourses(){
        return courseService.getCourses();
    }

    // get the course by id
    @GetMapping("/courses/{courseId}")
    public ResponseEntity<Course> getCourse(@PathVariable String courseId) {
         try {
             courseService.getCourse(Long.parseLong(courseId));
             return new ResponseEntity<>(HttpStatus.CREATED);
         }catch (Exception e){
             return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
         }
    }

    // add the course
    @PostMapping("/courses")
    public ResponseEntity<Course> addCourse(@RequestBody Course course){
        try{
            courseService.addCourse(course);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // update the course
    @PutMapping("/courses/{courseId}") ///{courseId}
    public ResponseEntity<Course> updateCourse(@PathVariable("courseId") Long courseId, @RequestBody Course course) {
        try {
            course.setId(courseId);
            courseService.updateCourse(course);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // delete the course by id
    @DeleteMapping("/courses/{courseId}")
    public ResponseEntity<HttpStatus> deleteCourse(@PathVariable String courseId){
        try {
            courseService.deleteCourse(Long.parseLong(courseId));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
