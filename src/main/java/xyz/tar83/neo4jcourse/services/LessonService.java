package xyz.tar83.neo4jcourse.services;

import java.util.List;

import org.springframework.stereotype.Service;

import xyz.tar83.neo4jcourse.models.Lesson;
import xyz.tar83.neo4jcourse.repositories.LessonRepository;

@Service
public class LessonService {
	private final LessonRepository lessonRepository;

	public LessonService(LessonRepository lessonRepository) {
		this.lessonRepository = lessonRepository;
	}

	public List<Lesson> getAllLessonsByCourseIdentifier(String indentifier) {
		return lessonRepository.findLessonsByCourseIdentifier(indentifier);
	}
}
