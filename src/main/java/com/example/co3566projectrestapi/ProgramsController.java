package com.example.co3566projectrestapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/api/cna/program")
public class ProgramsController {
    @Autowired

    private ProgramsRepository programsRepository;

    @PostMapping(path = "/add")
    public @ResponseBody Programs addNewProgram(@RequestBody Programs programs) {
        return programsRepository.save(programs);
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Programs> getAllPrograms() {
        return programsRepository.findAll();
    }

    @GetMapping(path = "/view/{pid}")
    public @ResponseBody Optional<Programs> getProgram(@PathVariable("pid") Integer pid) {
        return programsRepository.findById(pid);
    }

    @PutMapping(path = "/modify/{pid}")
    public @ResponseBody String modifyProgram(@PathVariable("pid") Integer pid, @RequestParam String programName
            , @RequestParam String campus) {
        Programs programs = programsRepository.findById(pid).get();
        programs.setProgramName(programName);
        programs.setCampus(campus);
        programsRepository.save(programs);
        return "Updated";
    }

    @DeleteMapping(path = "/delete/{pid}")
    public @ResponseBody String deleteProgram(@PathVariable("pid") Integer pid) {
        programsRepository.deleteById(pid);
        return "Deleted";
    }

}
