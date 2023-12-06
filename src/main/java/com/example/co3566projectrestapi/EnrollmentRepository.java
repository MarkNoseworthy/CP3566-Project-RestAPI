package com.example.co3566projectrestapi;

import org.springframework.data.repository.CrudRepository;

public interface EnrollmentRepository extends CrudRepository<Enrollment, Integer> {
    Iterable<Enrollment> findByCourseId(Integer courseId);
    Iterable<Enrollment> findByStudentId(Integer studentId);
}
