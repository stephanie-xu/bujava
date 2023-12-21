package modelTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.model.Day;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.Task;
import java.time.DayOfWeek;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for day class
 */
public class testDay {
  Day day;

  /**
   * initializes vars
   */
  @BeforeEach
  public void init() {
    day = new Day(DayOfWeek.FRIDAY);
    assertEquals(0, day.totalEvents());
    assertEquals(0, day.totalTasks());
  }


  /**
   * tests adding an event
   */
  @Test
  public void testAddEvent() {

    Event class_time = new Event("OOD", DayOfWeek.FRIDAY, "lecture",
        "1.4 hours", "11:40AM");
    day.addEvent(class_time);
    assertEquals(1, day.totalEvents());
    assertEquals(class_time, day.getEvents().get(0));
  }

  /**
   * tests adding fully loaded day
   */
  @Test
  public void testFullConstructor() {
    Event class_time = new Event("OOD", DayOfWeek.FRIDAY, "lecture",
        "1.4 hours", "11:40AM");
    Task oneTask = new Task("task1", DayOfWeek.FRIDAY, "todo", false);
    ArrayList<Event> events = new ArrayList<>();
    events.add(class_time);
    ArrayList<Task> tasks = new ArrayList<>();
    tasks.add(oneTask);

    Day loaded = new Day(events, tasks, DayOfWeek.FRIDAY);
    assertEquals(1, loaded.totalTasks());
    assertEquals(1, loaded.totalEvents());
  }

  /**
   * tests adding task
   */
  @Test
  public void testAddTask() {
    Task oneTask = new Task("task1", DayOfWeek.FRIDAY, "todo", false);
    Task twoTask = new Task("task2", DayOfWeek.FRIDAY, "finished", true);

    day.addTask(oneTask);
    day.addTask(twoTask);

    assertEquals(2, day.totalTasks());
    assertEquals(oneTask, day.getTasks().get(0));
  }

  /**
   * tests removing task
   */
  @Test
  public void testRemoveTask() {
    Task oneTask = new Task("task1", DayOfWeek.FRIDAY, "todo", false);
    day.addTask(oneTask);
    assertEquals(1, day.totalTasks());
    day.removeTask(oneTask);
    assertEquals(0, day.totalTasks());
  }

  /**
   * tests removing event
   */
  @Test
  public void testRemoveEvent() {
    Event class_time = new Event("OOD", DayOfWeek.FRIDAY, "lecture",
        "1.4 hours", "11:40AM");
    day.addEvent(class_time);
    assertEquals(1, day.totalEvents());
    day.removeEvent(class_time);
    assertEquals(0, day.totalEvents());
  }

  /**
   * tests calculations on day
   */
  @Test
  public void testCompletedCalcs() {
    assertEquals(0, day.numCompleted());
    assertEquals(0, day.remainingTasks());
    assertEquals(1.0, day.percentCompleted());

    Task oneTask = new Task("task1", DayOfWeek.FRIDAY, "todo", false);
    Task twoTask = new Task("task2", DayOfWeek.FRIDAY, "finished", true);

    day.addTask(oneTask);
    day.addTask(twoTask);

    assertEquals(1, day.numCompleted());

    assertEquals(1, day.remainingTasks());
    assertEquals(0.5, day.percentCompleted());
  }
}
