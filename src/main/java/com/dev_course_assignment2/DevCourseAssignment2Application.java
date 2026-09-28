package com.dev_course_assignment2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DevCourseAssignment2Application {

    public static void main(String[] args) {
        SpringApplication.run(DevCourseAssignment2Application.class, args);
    }

}
