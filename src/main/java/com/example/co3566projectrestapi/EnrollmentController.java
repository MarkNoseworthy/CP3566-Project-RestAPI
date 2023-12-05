package com.example.co3566projectrestapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/api/cna/enrollment")
public class EnrollmentController {
    @Autowired

    private EnrollmentRepository enrollmentRepository;

    @PostMapping(path = "/add")
    public @ResponseBody Enrollment addNewEnrollment(@RequestBody Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    @GetMapping(path = "/all/{courseId}")
    public @ResponseBody Optional<Enrollment> getCourseEnrollment(@PathVariable("courseId") Integer courseId) {
        return enrollmentRepository.findById(courseId);
    }
    @GetMapping(path = "/view/{studentId}")
    public @ResponseBody Optional<Enrollment> getStudentEnrollment(@PathVariable("studentId") Integer studentId) {
        return enrollmentRepository.findById(studentId);
    }
    @PutMapping(path = "/modify/{eid}")
    public @ResponseBody String modifyEnrollment(@PathVariable("eid") Integer eid, @RequestParam Integer courseId
    , @RequestParam Integer studentId) {
        Enrollment enrollment = enrollmentRepository.findById(eid).get();
        enrollment.setCourseId(courseId);
        enrollment.setStudentId(studentId);
        enrollmentRepository.save(enrollment);
        return "Updated";
    }

    @DeleteMapping(path = "/delete/{eid}")
    public @ResponseBody String deleteEnrollment(@PathVariable("eid") Integer eid) {
        enrollmentRepository.deleteById(eid);
        return "Deleted";
    }

}
