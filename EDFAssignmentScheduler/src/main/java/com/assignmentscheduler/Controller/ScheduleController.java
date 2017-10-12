package com.assignmentscheduler.Controller;

import com.assignmentscheduler.Services.AssignmentManager;
import com.assignmentscheduler.Services.SchedulingService;
import com.assignmentscheduler.models.Assignment;
import com.assignmentscheduler.models.Schedule;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class ScheduleController {

  @Autowired
  private SchedulingService schedulingService;

  private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
  private AssignmentManager assignmentManager = AssignmentManager.getInstance();

  @RequestMapping("/schedule")
  public Schedule getAssignments() throws ParseException {
    String dueDateString1 = "25/11/2017";

    String dueDateString2 = "11/10/2017";

    String dueDateString3 = "15/11/2017";

    try {
      Date parsedDueDate1 = this.formatter.parse(dueDateString1);

      Date parsedDueDate2 = this.formatter.parse(dueDateString2);

      Date parsedDueDate3 = this.formatter.parse(dueDateString3);

      Assignment assignment1 = new Assignment("Get Natalie a birthday present", parsedDueDate1, 5, 5);
      Assignment assignment2 = new Assignment("prepare for shopify", parsedDueDate2, 3, 5);
      Assignment assignment3 = new Assignment("Algebra homework", parsedDueDate3, 1, 3);


      this.assignmentManager.addAssignment(assignment1);
      this.assignmentManager.addAssignment(assignment2);
      this.assignmentManager.addAssignment(assignment3);

      val assignmentsList = this.assignmentManager.getAssignments();
      val assignments = assignmentsList.toArray(new Assignment[assignmentsList.size()]);

      return SchedulingService.edfScheduler(assignments);
    } catch (ParseException e) {
      throw (e);
    }
  }

  @RequestMapping("")
  public String Home() {
    return "Natalie's Scheduler";
  }

  @RequestMapping("/health")
  public String checkHealth() {
    return "All okay!";
  }
}
