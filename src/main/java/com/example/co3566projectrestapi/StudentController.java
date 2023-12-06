package com.example.co3566projectrestapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/api/cna/student")

public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @PostMapping(path = "/add")
    public @ResponseBody Student addNewUser (@RequestBody Student student) {
        return studentRepository.save(student);
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @GetMapping(path = "/view/{studentId}")
    public @ResponseBody Optional<Student> getStudent(@PathVariable("studentId") Integer studentId) {
        return studentRepository.findById(studentId);
    }

    @PutMapping(path = "/modify/{studentId}")
    public @ResponseBody Object modifyStudent(@PathVariable("studentId") Integer studentId, @RequestBody Student studentDetails) {
        if (studentRepository.findById(studentId).isPresent()) {
            Student student = studentRepository.findById(studentId).get();
            student.setFirstName(studentDetails.getFirstName());
            student.setLastName(studentDetails.getLastName());
            student.setEmail(studentDetails.getEmail());
            student.setAddress(studentDetails.getAddress());
            student.setCity(studentDetails.getCity());
            student.setPostal(studentDetails.getPostal());
            student.setPhone(studentDetails.getPhone());
            return studentRepository.save(student);
        }
        return "Does not exist.";
    }

    @DeleteMapping(path = "/delete/{studentId}")
    public @ResponseBody Object deleteStudent(@PathVariable("studentId") Integer studentId) {
        if (studentRepository.findById(studentId).isPresent()) {
            studentRepository.deleteById(studentId);
            return studentRepository.findById(studentId);
        }
        return "Does not exist.";
    }

}
