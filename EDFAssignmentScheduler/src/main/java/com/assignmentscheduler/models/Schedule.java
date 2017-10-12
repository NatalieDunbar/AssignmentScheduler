package com.assignmentscheduler.models;

import lombok.Data;

import java.util.Date;
import java.util.HashMap;

@Data
public class Schedule {


  /**
   * A mapping from the day that an assignment should be started to that assignment.
   */
  private HashMap<Date, Assignment> schedule;


  /**
   * a constructor that initializes an empty schedule.
   */
  public Schedule() {
    this.schedule = new HashMap<Date, Assignment>();
  }


  /**
   * @param startDate  the date that the assignment must be started
   * @param assignment the assignment that is being scheduled
   */
  public void put(Date startDate, Assignment assignment) {
    this.schedule.put(startDate, assignment);
  }
}
