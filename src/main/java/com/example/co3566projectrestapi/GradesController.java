package com.example.co3566projectrestapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/api/cna/grades")
public class GradesController {
    @Autowired

    private GradesRepository gradesRepository;

    @PostMapping(path = "/add")
    public @ResponseBody Grades addNewGrades(@RequestBody Grades grades) {
        return gradesRepository.save(grades);
    }

    @GetMapping(path = "/list-by-student/{studentId}")
    public @ResponseBody Iterable<Grades> getStudentGrades(@PathVariable("studentId") Integer studentId) {
        return gradesRepository.findByStudentId(studentId);
    }

    @GetMapping(path = "/list-by-course/{courseId}")
    public @ResponseBody Iterable<Grades> getCourseGrades(@PathVariable("courseId") Integer courseId) {
        return gradesRepository.findByCourseId(courseId);
    }

    @PutMapping(path = "/modify/{gid}")
    public @ResponseBody Object modifyGrade(@PathVariable("gid") Integer gid, @RequestBody Grades gradesDetails) {
        if (gradesRepository.findById(gid).isPresent()) {
            Grades grades = gradesRepository.findById(gid).get();
            grades.setStudentId(gradesDetails.getStudentId());
            grades.setCourseId(gradesDetails.getCourseId());
            grades.setGrade(gradesDetails.getGrade());
            return gradesRepository.save(grades);
        }
        return "Does not exist";
    }

    @DeleteMapping(path = "/delete/{gid}")
    public @ResponseBody Object deleteGrade(@PathVariable("gid") Integer gid) {
        if (gradesRepository.findById(gid).isPresent()) {
            gradesRepository.deleteById(gid);
            return gradesRepository.findById(gid);
        }
        return "Does not exist";
    }

}
