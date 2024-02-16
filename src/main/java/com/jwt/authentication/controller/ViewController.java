package com.jwt.authentication.controller;

import com.jwt.authentication.dto.UserRegistrationDTO;
import com.jwt.authentication.entities.Course;
import com.jwt.authentication.entities.Role;
import com.jwt.authentication.models.JwtRequest;
import com.jwt.authentication.services.CourseService;
import com.jwt.authentication.services.RoleService;
import com.jwt.authentication.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@Controller
public class ViewController {
    private Logger logger = LogManager.getLogger(ViewController.class);

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    @GetMapping("/")
    public String login(){
        return "login";
    }

    @GetMapping("/profile")
    public String profile(){
        return "profile";
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model) {
        List<Role> roles = roleService.getAllRoles();
        model.addAttribute("roles", roles);
        model.addAttribute("userRegistrationDto", new UserRegistrationDTO());
        return "registration";
    }

    @GetMapping("/error")
    public String error() {
        return "error";
    }

//    -----------------------------------------------------------------------------------------------------------------------------

    @GetMapping("/admin-dashboard")
    public String adminDashboard() {
        return "admin-dashboard"; // assuming you have a Thymeleaf template at src/main/resources/templates/admin/dashboard.html
    }

    @GetMapping("/user-dashboard")
    public String userDashboard() {
        return "user-dashboard"; // assuming you have a Thymeleaf template at src/main/resources/templates/user/dashboard.html
    }

    @GetMapping("/default-dashboard")
    public String defaultDashboard() {
        return "default-dashboard"; // assuming you have a Thymeleaf template at src/main/resources/templates/default/dashboard.html
    }

//    -----------------------------------------------------------------------------------------------------------------------------

    @GetMapping("/course-list")
    public String courseList(Model model){
        List<Course> listCourse = courseService.getCourses();
        model.addAttribute("listCourse", listCourse);
        return "course-list";
    }

    @GetMapping("/create-course")
    public String createCourse(){
        return "create-course";
    }

    @GetMapping("/edit-course")
    public String editCourse(@PathVariable Long id, Model model){
        Course course = courseService.getCourse(id);
        model.addAttribute("course", course);
        return "edit-course";
    }

    @GetMapping("/courses/edit/{id}")
    public String editCoursePage(@PathVariable Long id, Model model) {
        logger.info("----------Update Page Called------------");
        // Fetch the course by ID from the database
        Course course = courseService.getCourseById(id);
        System.out.println("---------course------------"+course);
        // Check if the course exists
        if (course != null) {
            model.addAttribute("course", course);
            return "edit-course"; // Return the name of your edit page Thymeleaf template
        } else {
            // Handle the case where the course is not found
            return "course-list"; // Redirect to the course list page if the course is not found
        }
    }



}
