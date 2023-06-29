package xyz.tar83.neo4jcourse.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xyz.tar83.neo4jcourse.models.Course;
import xyz.tar83.neo4jcourse.models.Lesson;
import xyz.tar83.neo4jcourse.objects.CourseDTO;
import xyz.tar83.neo4jcourse.services.CourseService;
import xyz.tar83.neo4jcourse.services.LessonService;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {
	private final CourseService courseService;
	private final LessonService lessonService;

	public CourseController(CourseService courseService, LessonService lessonService) {
		this.courseService = courseService;
		this.lessonService = lessonService;
	}

	@GetMapping()
	public ResponseEntity<List<CourseDTO>> courseIndex() {
		List<Course> courses = courseService.getAllCourses();
		
		List<CourseDTO> responseCourses = courses.stream().map((course) -> {
			CourseDTO courseDTO = new CourseDTO(course.getIdentifier(), course.getTitle(), course.getTeacher());
			List<Lesson> lessons = lessonService.getAllLessonsByCourseIdentifier(course.getIdentifier());
			courseDTO.setLessons(lessons);

			return courseDTO;
		}).collect(Collectors.toList());

		return new ResponseEntity<>(responseCourses, HttpStatus.OK);
	}

	@GetMapping("/{identifier}")
	public ResponseEntity<CourseDTO> courseDetails(@PathVariable String identifier) {
		Course course = courseService.getCourseByIdentifier(identifier);
		List<Lesson> lessons = lessonService.getAllLessonsByCourseIdentifier(identifier);
		CourseDTO courseDTO = new CourseDTO(course.getIdentifier(), course.getTitle(), course.getTeacher());

		courseDTO.setLessons(lessons);

		return new ResponseEntity<>(courseDTO, HttpStatus.OK);
	}
}
