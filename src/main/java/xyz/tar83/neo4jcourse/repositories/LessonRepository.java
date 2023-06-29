package xyz.tar83.neo4jcourse.repositories;

import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import xyz.tar83.neo4jcourse.models.Lesson;

public interface LessonRepository extends Neo4jRepository<Lesson, Long> {
	@Query("MATCH (:Course {identifier: $identifier})<-[:BELONGS_TO]-(lessons:Lesson) RETURN lessons")
	List<Lesson> findLessonsByCourseIdentifier(String identifier);
}
