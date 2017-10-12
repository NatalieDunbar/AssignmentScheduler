package com.assignmentscheduler.Services;

import com.assignmentscheduler.models.Assignment;
import com.assignmentscheduler.models.Schedule;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;


@Service
public class SchedulingService {

  /**
   * An 'earliest deadline first' algorithm that schedules the assignments by sorting them by their deadline and
   * scheduling them in that order.
   *
   * @param assignments the assignments to be added to the schedule.
   * @return a schedule that maps the assignments to the latest day they can be started.
   */
  public static Schedule edfScheduler(Assignment[] assignments) {
    Arrays.sort(assignments, Assignment.AssignmentComparator);

    // the day that you really have to get the assignment done by!
    Date realDueDate = assignments[0].getDueDate();
    Schedule procrastinatorSchedule = new Schedule();

    for (Assignment assignment : assignments) {

      // Find the day the assignment needs to be done by
      realDueDate = assignment.getDueDate().before(realDueDate) ? assignment.getDueDate() : realDueDate;

      //subtract the number of days that it will take to do the assignment to find start date
      final java.util.Calendar cal = GregorianCalendar.getInstance();
      cal.setTime(realDueDate);
      int subDays = -assignment.getDays();
      cal.add(GregorianCalendar.DATE, subDays);

      //Add the start date and assignment to the schedule
      procrastinatorSchedule.put(cal.getTime(), assignment);

      cal.add(GregorianCalendar.DATE, -1);
      realDueDate = cal.getTime(); // the new first free day
    }

    return procrastinatorSchedule;
  }
}
