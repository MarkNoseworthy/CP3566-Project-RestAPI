package com.example.co3566projectrestapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/api/cna/course")
public class CourseController {
    @Autowired

    private CourseRepository courseRepository;

    @PostMapping(path = "/add")
    public @ResponseBody String addNewCourse(@RequestParam String courseName, @RequestParam String courseNumber
    , @RequestParam Integer capacity, @RequestParam Integer year, @RequestParam String semester
    , @RequestParam Integer pid) {
        Course course = new Course();
        course.setCourseName(courseName);
        course.setCourseNumber(courseNumber);
        course.setCapacity(capacity);
        course.setSemester(semester);
        course.setYear(year);
        course.setPid(pid);
        courseRepository.save(course);
        return "Saved";
    }
    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @GetMapping(path = "/view/{courseId}")
    public @ResponseBody Optional<Course> getCourse(@PathVariable("courseId") Integer courseId) {
        return courseRepository.findById(courseId);
    }

    @PutMapping(path = "/modify/{courseId}")
    public @ResponseBody String modifyCourse(@PathVariable("courseId") Integer courseId, @RequestParam String courseName
            , @RequestParam String courseNumber, @RequestParam Integer capacity, @RequestParam Integer year
    , @RequestParam String semester, @RequestParam Integer pid) {
        Course course = courseRepository.findById(courseId).get();
        course.setCourseName(courseName);
        course.setCourseNumber(courseNumber);
        course.setCapacity(capacity);
        course.setYear(year);
        course.setSemester(semester);
        course.setPid(pid);
        courseRepository.save(course);
        return "Updated";
    }

    @DeleteMapping(path = "/delete/{courseId}")
    public @ResponseBody String deleteCourse(@PathVariable("courseId") Integer courseId) {
        courseRepository.deleteById(courseId);
        return "Deleted";
    }

}