package com.assignmentscheduler.models;

import lombok.Getter;

import java.util.Comparator;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class Assignment implements Comparable<Assignment> {

  private final String name;
  private final Date dueDate;
  private final int days;
  private final int priority;
  private final int id;

  static AtomicInteger nextId = new AtomicInteger();

  public Assignment(String name, Date dueDate, int days, int priority) {

    this.name = name;
    this.dueDate = dueDate;
    this.days = days;
    this.priority = priority;
    this.id = nextId.incrementAndGet();
  }

  public int compareTo(Assignment anotherAssignment) {
    if (this.dueDate.compareTo(anotherAssignment.getDueDate()) == 0) {
      return (this.priority < anotherAssignment.priority) ? 0 : 1;
    } else {
      return this.dueDate.compareTo(anotherAssignment.getDueDate());
    }
  }

  public static Comparator<Assignment> AssignmentComparator = new Comparator<Assignment>() {

    public int compare(Assignment assignment1, Assignment assignment2) {
      // return assignments in descending order by due date (primary) and priority (secondary)
      return assignment2.compareTo(assignment1);
    }
  };

}
