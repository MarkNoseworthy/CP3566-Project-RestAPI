package com.example.co3566projectrestapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/api/cna/enrollment")
public class EnrollmentController {
    @Autowired

    private EnrollmentRepository enrollmentRepository;

    @PostMapping(path = "/add")
    public @ResponseBody Enrollment addNewEnrollment(@RequestBody Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    @GetMapping(path = "/list-by-course/{courseId}")
    public @ResponseBody Iterable<Enrollment> getCourseEnrollment(@PathVariable("courseId") Integer courseId) {
        return enrollmentRepository.findByCourseId(courseId);
    }
    @GetMapping(path = "/list-by-student/{studentId}")
    public @ResponseBody Iterable<Enrollment> getStudentEnrollment(@PathVariable("studentId") Integer studentId) {
        return enrollmentRepository.findByStudentId(studentId);
    }
    @PutMapping(path = "/modify/{eid}")
    public @ResponseBody Object modifyEnrollment(@PathVariable("eid") Integer eid, @RequestBody Enrollment enrollmentDetails) {
        if (enrollmentRepository.findById(eid).isPresent()) {
            Enrollment enrollment = enrollmentRepository.findById(eid).get();
            enrollment.setCourseId(enrollmentDetails.getCourseId());
            enrollment.setStudentId(enrollmentDetails.getStudentId());
            return enrollmentRepository.save(enrollment);
        }
        return "Does not exist.";
    }

    @DeleteMapping(path = "/delete/{eid}")
    public @ResponseBody Object deleteEnrollment(@PathVariable("eid") Integer eid) {
        if (enrollmentRepository.findById(eid).isPresent()) {
            enrollmentRepository.deleteById(eid);
            return enrollmentRepository.findById(eid);
        }
        return "Does not exist.";
    }

}
