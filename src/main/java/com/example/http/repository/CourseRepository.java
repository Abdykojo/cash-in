package com.example.http.repository;

import com.example.http.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Course getCourseByCurrency (String courseSum);
}
