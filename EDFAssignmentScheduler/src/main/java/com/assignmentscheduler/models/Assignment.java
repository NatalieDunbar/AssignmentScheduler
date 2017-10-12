package com.assignmentscheduler.models;

import lombok.Getter;

import java.util.Comparator;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class Assignment implements Comparable<Assignment> {

  /**
   * the assignment's name
   */
  private final String name;

  /**
   * the date that the assignment must be done by
   */
  private final Date dueDate;

  /**
   * the number of days that it will take to complete the assignment
   */
  private final int days;

  /**
   * the priority of the assignment
   */
  private final int priority;

  /**
   * a unique identifier for the assignment used for assignment deletion
   */
  private final int id;


  /**
   * generates unique ids for the assignments
   */
  static AtomicInteger nextId = new AtomicInteger();


  /**
   * Assignment Constructor
   *
   * @param name     a name for the assignment
   * @param dueDate  the day that the assignment must be done by
   * @param days     the number of days it will take to complete the assignment
   * @param priority How important it is that this assignment is scheduled (in case more than capacity is scheduled)
   */
  public Assignment(String name, Date dueDate, int days, int priority) {

    this.name = name;
    this.dueDate = dueDate;
    this.days = days;
    this.priority = priority;
    this.id = nextId.incrementAndGet();
  }


  /**
   * Compares assignments first by the due date, and then by the priority
   *
   * @param anotherAssignment the other assignment being compared to
   * @return 1 if the assignment should be completed before the other assignment, and 0 or less otherwise
   */
  public int compareTo(Assignment anotherAssignment) {
    if (this.dueDate.compareTo(anotherAssignment.getDueDate()) == 0) {
      return (this.priority < anotherAssignment.priority) ? 0 : 1;
    } else {
      return this.dueDate.compareTo(anotherAssignment.getDueDate());
    }
  }


  /**
   * The comparator function used for sorting assignments
   */
  public static Comparator<Assignment> AssignmentComparator = new Comparator<Assignment>() {

    public int compare(Assignment assignment1, Assignment assignment2) {
      // return assignments in descending order by due date (primary) and priority (secondary)
      return assignment2.compareTo(assignment1);
    }
  };

}
