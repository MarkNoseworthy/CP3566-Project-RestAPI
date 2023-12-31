package com.example.co3566projectrestapi;

import org.springframework.data.repository.CrudRepository;

public interface GradesRepository extends CrudRepository<Grades, Integer> {
    Iterable<Grades> findByCourseId(Integer courseId);
    Iterable<Grades> findByStudentId(Integer studentId);
}
