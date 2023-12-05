package com.example.co3566projectrestapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/api/cna/grades")
public class GradesController {
    @Autowired

    private GradesRepository gradesRepository;

    @PostMapping(path = "/add")
    public @ResponseBody Grades addNewGrades(@RequestBody Grades grades) {
        return gradesRepository.save(grades);
    }

    @GetMapping(path = "/all/{studentId}")
    public @ResponseBody Optional<Grades> getStudentGrades(@PathVariable("studentId") Integer studentId) {
        return gradesRepository.findById(studentId);
    }

    @GetMapping(path = "/all/{courseId}")
    public @ResponseBody Optional<Grades> getCourseGrades(@PathVariable("courseId") Integer courseId) {
        return gradesRepository.findById(courseId);
    }

    @PutMapping(path = "/modify/{gid}")
    public @ResponseBody String modifyGrade(@PathVariable("gid") Integer gid, @RequestParam Integer studentId
    , @RequestParam Integer courseId, @RequestParam Integer grade) {
        Grades grades = gradesRepository.findById(gid).get();
        grades.setStudentId(studentId);
        grades.setCourseId(courseId);
        grades.setGrade(grade);
        gradesRepository.save(grades);
        return "Updated";
    }

    @DeleteMapping(path = "/delete/{gid}")
    public @ResponseBody String deleteGrade(@PathVariable("gid") Integer gid) {
        gradesRepository.deleteById(gid);
        return "Deleted";
    }

}
