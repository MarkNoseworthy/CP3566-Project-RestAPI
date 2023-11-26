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
    public @ResponseBody String addNewUser (@RequestParam String firstName, @RequestParam String lastName
    , @RequestParam String email, @RequestParam String address, @RequestParam String city
    , @RequestParam String postal, @RequestParam String phone) {
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setAddress(address);
        student.setCity(city);
        student.setPostal(postal);
        student.setPhone(phone);
        studentRepository.save(student);
        return "Saved";
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
    public @ResponseBody String modifyStudent(@PathVariable("studentId") Integer studentId, @RequestParam String firstName
            , @RequestParam String lastName, @RequestParam String email, @RequestParam String address, @RequestParam String city
            , @RequestParam String postal, @RequestParam String phone) {
        Student student = studentRepository.findById(studentId).get();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setAddress(address);
        student.setCity(city);
        student.setPostal(postal);
        student.setPhone(phone);
        studentRepository.save(student);
        return "Updated";
    }

    @DeleteMapping(path = "/delete/{studentId}")
    public @ResponseBody String deleteStudent(@PathVariable("studentId") Integer studentId) {
        studentRepository.deleteById(studentId);
        return "Deleted";
    }

}
