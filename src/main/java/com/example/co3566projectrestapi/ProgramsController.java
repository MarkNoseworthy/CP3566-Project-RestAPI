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
    public @ResponseBody Object modifyProgram(@PathVariable("pid") Integer pid, @RequestBody Programs programsDetails) {
        if (programsRepository.findById(pid).isPresent()) {
            Programs programs = programsRepository.findById(pid).get();
            programs.setProgramName(programsDetails.getProgramName());
            programs.setCampus(programsDetails.getCampus());
            return programsRepository.save(programs);
        }
        return "Does not exist.";
    }

    @DeleteMapping(path = "/delete/{pid}")
    public @ResponseBody Object deleteProgram(@PathVariable("pid") Integer pid) {
        if (programsRepository.findById(pid).isPresent()) {
            programsRepository.deleteById(pid);
            return programsRepository.findById(pid);
        }
        return "Does not exist.";
    }

}
