import com.assignmentscheduler.models.Assignment;
import com.assignmentscheduler.models.Schedule;
import com.assignmentscheduler.services.AssignmentService;
import com.assignmentscheduler.services.SchedulingService;
import lombok.val;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TestSchedulingService {

  private final static SimpleDateFormat FORMATTER = new SimpleDateFormat("dd/MM/yyyy");
  private final static AssignmentService ASSIGNMENT_SERVICE = AssignmentService.getInstance();


  @Test
  public void testSingleAssignment() {
    String dueDateString = "25/11/2017";
    String startDateString = "20/11/2017";

    val expected = new Schedule();

    try {
      Date parsedDueDate = FORMATTER.parse(dueDateString);
      Date parsedStartDate = FORMATTER.parse(startDateString);

      Assignment assignment = new Assignment("Get Natalie a birthday present", parsedDueDate, 5, 5);

      // add the expected pairing to the schedule
      expected.put(parsedStartDate, assignment);

      ASSIGNMENT_SERVICE.addAssignment(assignment);

      val assignmentsList = ASSIGNMENT_SERVICE.getAssignments();
      val assignments = assignmentsList.toArray(new Assignment[assignmentsList.size()]);

      val actual = SchedulingService.edfScheduler(assignments);

      assertEquals(expected, actual);
    } catch (ParseException e) {
      fail();
    }
  }


  @Test
  public void testMultipleAssignmentsNoOverlap() {
    String dueDateString1 = "25/11/2017";
    String startDateString1 = "20/11/2017";

    String dueDateString2 = "11/10/2017";
    String startDateString2 = "8/10/2017";


    String dueDateString3 = "15/11/2017";
    String startDateString3 = "14/11/2017";

    val expected = new Schedule();

    try {
      Date parsedDueDate1 = FORMATTER.parse(dueDateString1);
      Date parsedStartDate1 = FORMATTER.parse(startDateString1);

      Date parsedDueDate2 = FORMATTER.parse(dueDateString2);
      Date parsedStartDate2 = FORMATTER.parse(startDateString2);

      Date parsedDueDate3 = FORMATTER.parse(dueDateString3);
      Date parsedStartDate3 = FORMATTER.parse(startDateString3);

      Assignment assignment1 = new Assignment("Get Natalie a birthday present", parsedDueDate1, 5, 5);
      Assignment assignment2 = new Assignment("prepare for shopify", parsedDueDate2, 3, 5);
      Assignment assignment3 = new Assignment("Algebra homework", parsedDueDate3, 1, 3);

      // add the expected pairing to the schedule
      expected.put(parsedStartDate1, assignment1);
      expected.put(parsedStartDate2, assignment2);
      expected.put(parsedStartDate3, assignment3);

      ASSIGNMENT_SERVICE.addAssignment(assignment1);
      ASSIGNMENT_SERVICE.addAssignment(assignment2);
      ASSIGNMENT_SERVICE.addAssignment(assignment3);

      val assignmentsList = ASSIGNMENT_SERVICE.getAssignments();
      val assignments = assignmentsList.toArray(new Assignment[assignmentsList.size()]);

      val actual = SchedulingService.edfScheduler(assignments);

      assertEquals(expected, actual);
    } catch (ParseException e) {
      fail();
    }
  }


  @Test
  public void testMultipleAssignmentsWithOverlap() {
    String dueDateString1 = "25/11/2017";
    String startDateString1 = "20/11/2017";

    String dueDateString2 = "15/11/2017";
    String startDateString2 = "8/11/2017";

    String dueDateString3 = "13/11/2017";
    String startDateString3 = "3/11/2017";

    val expected = new Schedule();

    try {
      Date parsedDueDate1 = FORMATTER.parse(dueDateString1);
      Date parsedStartDate1 = FORMATTER.parse(startDateString1);

      Date parsedDueDate2 = FORMATTER.parse(dueDateString2);
      Date parsedStartDate2 = FORMATTER.parse(startDateString2);

      Date parsedDueDate3 = FORMATTER.parse(dueDateString3);
      Date parsedStartDate3 = FORMATTER.parse(startDateString3);

      Assignment assignment1 = new Assignment("Get Natalie a birthday present", parsedDueDate1, 5, 5);
      Assignment assignment2 = new Assignment("prepare for shopify", parsedDueDate2, 7, 5);
      Assignment assignment3 = new Assignment("Algebra homework", parsedDueDate3, 4, 4);

      // add the expected pairing to the schedule
      expected.put(parsedStartDate1, assignment1);
      expected.put(parsedStartDate2, assignment2);
      expected.put(parsedStartDate3, assignment3);

      ASSIGNMENT_SERVICE.addAssignment(assignment1);
      ASSIGNMENT_SERVICE.addAssignment(assignment2);
      ASSIGNMENT_SERVICE.addAssignment(assignment3);

      val assignmentsList = ASSIGNMENT_SERVICE.getAssignments();
      val assignments = assignmentsList.toArray(new Assignment[assignmentsList.size()]);

      val actual = SchedulingService.edfScheduler(assignments);

      assertEquals(expected, actual);
    } catch (ParseException e) {
      fail();
    }
  }
}
