package com.assignmentscheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class AssignmentSchedulerApp extends SpringBootServletInitializer {

  public static void main(String[] args) {
    SpringApplication.run(AssignmentSchedulerApp.class, args);
  }

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    return builder.sources(AssignmentSchedulerApp.class);
  }
}
