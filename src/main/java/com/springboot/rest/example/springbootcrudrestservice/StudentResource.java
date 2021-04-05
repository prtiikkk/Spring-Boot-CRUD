package com.springboot.rest.example.springbootcrudrestservice;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * The Class StudentResource.
 */
@RestController
public class StudentResource {

  @Autowired
  private StudentRepository studentRepository;


  /**
   * Retrieve all students.
   *
   * @return the list of {@link Student}
   */
  @GetMapping("/students")
  public List<Student> retrieveAllStudents() {
    return studentRepository.findAll();
  }

  /**
   * Retrieve student.
   *
   * @param id the id
   * @return the student
   * @throws Exception the exception
   */
  @GetMapping("/students/{id}")
  public Student retrieveStudent(@PathVariable long id) {
    Optional<Student> student = studentRepository.findById(id);
    if (!student.isPresent()) {
      throw new StudentNotFoundException("id-" + id);
    }
    return student.get();
  }

  /**
   * Delete student.
   *
   * @param id the id
   */
  @DeleteMapping("/students/{id}")
  public void deleteStudent(@PathVariable long id) {
    studentRepository.deleteById(id);
  }

  /**
   * Creates the student.
   *
   * @param student the student
   * @return the response entity
   */
  @PostMapping("/students")
  public ResponseEntity<Object> createStudent(@RequestBody Student student) {
    Student savedStudent = studentRepository.save(student);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(savedStudent.getId()).toUri();
    return ResponseEntity.created(location).build();

  }

  /**
   * Update student.
   *
   * @param student the student
   * @param id the id
   * @return the response entity
   */
  @PutMapping("/students/{id}")
  public ResponseEntity<Object> updateStudent(@RequestBody Student student, @PathVariable long id) {
    Optional<Student> studentOptional = studentRepository.findById(id);
    if (!studentOptional.isPresent())
      return ResponseEntity.notFound().build();
    student.setId(id);
    studentRepository.save(student);
    return ResponseEntity.noContent().build();
  }

}
