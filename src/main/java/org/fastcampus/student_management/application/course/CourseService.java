package org.fastcampus.student_management.application.course;

import java.util.ArrayList;
import java.util.List;
import org.fastcampus.student_management.application.course.dto.CourseInfoDto;
import org.fastcampus.student_management.application.student.StudentService;
import org.fastcampus.student_management.domain.Course;
import org.fastcampus.student_management.domain.DayOfWeek;
import org.fastcampus.student_management.domain.Student;
import org.fastcampus.student_management.repo.CourseRepository;

public class CourseService {
  private final CourseRepository courseRepository;
  private final StudentService studentService;

  public CourseService(CourseRepository courseRepository, StudentService studentService) {
    this.courseRepository = courseRepository;
    this.studentService = studentService;
  }

  public void registerCourse(CourseInfoDto courseInfoDto) {
    Student student = studentService.getStudent(courseInfoDto.getStudentName());
    Course course = new Course(student, courseInfoDto.getCourseName(), courseInfoDto.getFee(), courseInfoDto.getDayOfWeek(), courseInfoDto.getCourseTime());
    courseRepository.save(course);
  }

  public List<CourseInfoDto> getCourseDayOfWeek(DayOfWeek dayOfWeek) {
    // TODO: 과제 구현 부분
    List<Course> list = courseRepository.getCourseDayOfWeek(dayOfWeek);
    List<CourseInfoDto> result = new ArrayList<>();
    for (Course course : list) {
      result.add(new CourseInfoDto(course));
    }

    return result;
  }

  public void changeFee(String studentName, int fee) {
    // TODO: 과제 구현 부분
    List<Course> list = courseRepository.getCourseListByStudent(studentName);
    List<Course> newList = new ArrayList<>();
    for (Course course : list) {
      Student student = studentService.getStudent(course.getStudentName());
      newList.add(new Course(student, course.getCourseName(), fee, course.getDayOfWeek(), course.getCourseTime()));
    }
    courseRepository.saveCourses(newList);
  }
}
