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
   * you can optionally name the schedule and have multiple schedules
   */
  private String name;

  public Schedule() {
    this.schedule = new HashMap<Date, Assignment>();
  }

  public void put(Date startDate, Assignment assignment) {
    this.schedule.put(startDate, assignment);
  }
}
