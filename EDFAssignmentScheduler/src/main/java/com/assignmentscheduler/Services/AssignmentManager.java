package com.assignmentscheduler.Services;

import com.assignmentscheduler.models.Assignment;
import lombok.Getter;

import java.util.ArrayList;

/**
 * Singleton class that contains all of the assignments
 */
public class AssignmentManager {

  private static AssignmentManager instance;

  private AssignmentManager() {
    assignments = new ArrayList<Assignment>();
  }

  public static AssignmentManager getInstance() {
    if (instance == null) {
      instance = new AssignmentManager();
    }
    return instance;
  }

  @Getter
  private ArrayList<Assignment> assignments;

  public void addAssignment(Assignment assignment) {
    this.assignments.add(assignment);
  }

}
