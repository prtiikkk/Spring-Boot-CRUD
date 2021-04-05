package com.springboot.rest.example.springbootcrudrestservice;

public class StudentNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new student not found exception.
   *
   * @param exception the exception
   */
  public StudentNotFoundException(String exception) {
    super(exception);
  }
}
