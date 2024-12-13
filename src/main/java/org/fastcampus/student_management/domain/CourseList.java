package org.fastcampus.student_management.domain;

import java.util.List;

public class CourseList {
  private List<Course> courses;

  public CourseList(List<Course> courses){
    this.courses = courses;
  }

  public void changeAllCourseFee(int fee){
    for(Course course : courses){
      if(course.isSameDay(DayOfWeek.SATURDAY) || course.isSameDay(DayOfWeek.SUNDAY)){
        course.changeFee((int)(fee * 1.5));
        continue;
      }
      course.changeFee(fee);
    }
  }


}
