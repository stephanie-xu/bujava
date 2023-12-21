package modelTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa05.model.Task;
import java.time.DayOfWeek;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the Task class
 */
public class testTask {

  Task task;

  /**
   * Initializes the task before each test
   */
  @BeforeEach
  public void init() {
    task = new Task("Do homework", DayOfWeek.FRIDAY, "OOD", false);
  }

  /**
   * Test getters and setters of complete field
   */
  @Test
  public void testCompletion() {
    assertFalse(task.isComplete());
    task.setComplete(true);
    assertTrue(task.isComplete());
  }

  /**
   * Tests string formatting output
   */
  @Test
  public void testToString() {
    Task noDesc = new Task("example", DayOfWeek.MONDAY, "", true);
    assertEquals("Do homework\nOOD\nDone: No", task.toString());
    assertEquals("example\nDone: Yes", noDesc.toString());
  }
}
