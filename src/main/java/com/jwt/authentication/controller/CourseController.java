package com.jwt.authentication.controller;

import com.jwt.authentication.entities.Course;
import com.jwt.authentication.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    // get the all courses
    @GetMapping()
    public List<Course> getCourses(){
        return courseService.getCourses();
    }

    // get the course by id
    @GetMapping("/{courseId}")
    public Course getCourse(@PathVariable String courseId) {
        return courseService.getCourse(Long.parseLong(courseId));
    }

    // add the course
    @PostMapping()
    public Course addCourse(@RequestBody Course course){
        return courseService.addCourse(course);
    }

    // update the course
    @PutMapping("/{courseId}") ///{courseId}
    public Course updateCourse(@PathVariable("courseId") Long courseId, @RequestBody Course course) { //,
        course.setId(courseId);
        return courseService.updateCourse(course);
//		return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
    }


    // delete the course by id
    @DeleteMapping("/{courseId}")
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
