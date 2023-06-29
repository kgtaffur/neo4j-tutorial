package xyz.tar83.neo4jcourse.services;

import java.util.List;

import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import xyz.tar83.neo4jcourse.models.Course;
import xyz.tar83.neo4jcourse.repositories.CourseRepository;

@Service
public class CourseService {
	private final CourseRepository courseRepository;

	public CourseService(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	public List<Course> getAllCourses() {
		return courseRepository.findAll();
	}

	public Course getCourseByIdentifier(String identifier) {
		return courseRepository.findCourseByIdentifier(identifier)
				.orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404)));
	}
}
