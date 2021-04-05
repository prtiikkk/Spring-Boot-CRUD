package com.springboot.rest.example.springbootcrudrestservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The Interface StudentRepository.
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
