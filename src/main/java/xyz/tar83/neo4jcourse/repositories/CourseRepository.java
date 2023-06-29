package xyz.tar83.neo4jcourse.repositories;

import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import xyz.tar83.neo4jcourse.models.Course;

public interface CourseRepository extends Neo4jRepository<Course, Long> {
	Optional<Course> findCourseByIdentifier(String identifier);
}
