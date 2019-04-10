package main.java.memoranda;

import main.java.flashcourse.Course;

public interface CourseListener {
  void courseChange(Course course, boolean toSaveCurrentNote);
}
