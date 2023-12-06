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
    public @ResponseBody Course addNewCourse(@RequestBody Course course) {
        return courseRepository.save(course);
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
    public @ResponseBody Object modifyCourse(@PathVariable("courseId") Integer courseId, @RequestBody Course courseDetails) {
        if (courseRepository.findById(courseId).isPresent()) {
            Course course = courseRepository.findById(courseId).get();
            course.setCourseName(courseDetails.getCourseName());
            course.setCourseNumber(courseDetails.getCourseNumber());
            course.setCapacity(courseDetails.getCapacity());
            course.setYear(courseDetails.getYear());
            course.setSemester(courseDetails.getSemester());
            course.setPid(courseDetails.getPid());
            return courseRepository.save(course);
        }
        return "Does not exist.";
    }

    @DeleteMapping(path = "/delete/{courseId}")
    public @ResponseBody Object deleteCourse(@PathVariable("courseId") Integer courseId) {
        if (courseRepository.findById(courseId).isPresent()) {
            courseRepository.deleteById(courseId);
            return courseRepository.findById(courseId);
        }
        return "Does not exist.";
    }

}