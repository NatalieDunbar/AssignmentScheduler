package com.assignmentscheduler.controllers;

import com.assignmentscheduler.models.Assignment;
import com.assignmentscheduler.models.Schedule;
import com.assignmentscheduler.services.AssignmentService;
import com.assignmentscheduler.services.SchedulingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

@RestController
public class ScheduleController {


  private final static SimpleDateFormat FORMATTER = new SimpleDateFormat("dd/MM/yyyy");
  private AssignmentService assignmentService = AssignmentService.getInstance();


  /**
   * Creates and returns the schedule based on the currently added assignments.
   *
   * @return A schedule mapping the day an assignment needs to be started to the assignment.
   * @throws ParseException
   */
  @RequestMapping(value = "/schedule", method = RequestMethod.GET)
  public Schedule getSchedule() throws ParseException {
    ArrayList<Assignment> assignmentList = AssignmentService.getInstance().getAssignments();
    Assignment[] assignments = assignmentList.toArray(new Assignment[assignmentList.size()]);
    Schedule procrastinatorsSchedule = SchedulingService.edfScheduler(assignments);
    return procrastinatorsSchedule;
  }


  /**
   * Add an assignment to the current list of assignments.
   *
   * @param assignmentString the JSON representing an assignment to add to the list of assignments.
   */
  @RequestMapping(value = "/assignment", method = RequestMethod.PUT)
  public void addAssignment(@RequestParam String assignmentString) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    Map<String, String> assignmentMap = mapper.readValue(assignmentString, Map.class);
    String name = assignmentMap.get("name");
    String dueDateString = assignmentMap.get("dueDate");

    try {
      Date dueDate = FORMATTER.parse(dueDateString);
      int days = Integer.parseInt(assignmentMap.get("days"));
      int priority = Integer.parseInt(assignmentMap.get("priority"));

      Assignment assignment = new Assignment(name, dueDate, days, priority);

      AssignmentService.getInstance().addAssignment(assignment);
    } catch (ParseException e) {
      System.err.print("Date formatter threw exception" + e);
    }
  }


  /**
   * Get the current list of assignments.
   *
   * @return
   */
  @RequestMapping(value = "/assignment", method = RequestMethod.GET)
  public ArrayList<Assignment> getAssignmentList() {
    return AssignmentService.getInstance().getAssignments();
  }


  /**
   * Delete a specified assignment from the list of assignment.
   *
   * @param assignmentId the id of the assignment to be deleted.
   */
  @RequestMapping(value = "/assignment", method = RequestMethod.DELETE)
  public void deleteAssignment(@RequestParam String assignmentId) {
    AssignmentService.getInstance().deleteAssignment(assignmentId);
  }


  /**
   * @return A String for the current default landing page.
   */
  @RequestMapping("")
  public String Home() {
    return "Natalie's Scheduler";
  }


  /**
   * @return a message indicating the endpoints are being hit.
   */
  @RequestMapping("/health")
  public String checkHealth() {
    return "All okay!";
  }
}
