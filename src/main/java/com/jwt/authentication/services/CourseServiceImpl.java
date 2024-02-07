package com.jwt.authentication.services;

import com.jwt.authentication.entities.Course;
import com.jwt.authentication.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService{

    private CourseRepository courseRepository;

    @Override
    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourse(Long courseId) {
        Optional<Course> optionalCourse = courseRepository.findById(courseId);
        return optionalCourse.get();
    }

    @Override
    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(Course course) {
        Course existingCourse = courseRepository.findById(course.getId()).get();
        System.out.println(existingCourse+"existingCourse");
        existingCourse.setTitle(course.getTitle());
        existingCourse.setDescription(course.getDescription());
        return courseRepository.save(existingCourse);
    }

    @Override
    public void deleteCourse(Long courseId) {
        courseRepository.deleteById(courseId);
    }
}
