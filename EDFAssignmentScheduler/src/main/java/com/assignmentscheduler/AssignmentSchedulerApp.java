package com.assignmentscheduler;

import com.assignmentscheduler.Services.AssignmentManager;
import com.assignmentscheduler.Services.SchedulingService;
import com.assignmentscheduler.models.Assignment;
import com.assignmentscheduler.models.Schedule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import java.util.ArrayList;

@SpringBootApplication
public class AssignmentSchedulerApp extends SpringBootServletInitializer {

  AssignmentManager assignmentManager = AssignmentManager.getInstance();

  public Schedule createSchedule() {
    ArrayList<Assignment> assignmentList = this.assignmentManager.getAssignments();
    Assignment[] assignments = assignmentList.toArray(new Assignment[assignmentList.size()]);
    Schedule procrastinatorsSchedule = SchedulingService.edfScheduler(assignments);
    return procrastinatorsSchedule;
  }

  public static void main(String[] args) {
    SpringApplication.run(AssignmentSchedulerApp.class, args);
  }

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    return builder.sources(AssignmentSchedulerApp.class);
  }


  // need to be able to add assignments

  // then get schedule calls the scheduler (dont call every time)

  // have the output printed nicely

  // if you would have had to start something earlier than current date, display it in a cute way like "you would have
  // had to start the x days ago!
}
