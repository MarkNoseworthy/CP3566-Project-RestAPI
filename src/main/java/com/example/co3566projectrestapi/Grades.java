package com.example.co3566projectrestapi;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Grades {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer gid;
    private Integer studentId;
    private Integer courseId;
    private Integer grade;
}
