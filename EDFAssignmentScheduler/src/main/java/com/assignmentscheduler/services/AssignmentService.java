package com.assignmentscheduler.services;

import com.assignmentscheduler.models.Assignment;
import lombok.Getter;

import java.util.ArrayList;

/**
 * Singleton class that contains all of the assignments
 */
public class AssignmentService {


  /**
   * the singleton instance of the Assignment Service
   */
  private static AssignmentService instance;


  /**
   * the private constructor that initializes an empty assignment list.
   */
  private AssignmentService() {
    assignments = new ArrayList<Assignment>();
  }


  /**
   * Creates and returns an instance of the AssignmentService if none exist, otherwise returns the existing instance.
   *
   * @return The singleton AssignmentService instance
   */
  public static AssignmentService getInstance() {
    if (instance == null) {
      instance = new AssignmentService();
    }
    return instance;
  }


  /**
   * The list of assignments
   */
  @Getter
  private ArrayList<Assignment> assignments;


  /**
   * Adds an assignment to the list of assignments.
   *
   * @param assignment the assignment being added
   */
  public void addAssignment(Assignment assignment) {
    this.assignments.add(assignment);
  }


  /**
   * Deletes an assignment from the list of assignments.
   *
   * @param assignmentId the assignment being deleted
   */
  public void deleteAssignment(String assignmentId) {
    int id = Integer.parseInt(assignmentId);
    for (Assignment assignment : this.assignments) {
      if (assignment.getId() == id) {
        assignments.remove(assignment);
      }
    }
  }

}
