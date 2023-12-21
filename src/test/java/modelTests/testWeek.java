package modelTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.model.Day;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.Week;
import java.time.DayOfWeek;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class testWeek {
  Week week;
  Day monday = new Day(DayOfWeek.MONDAY);
  @BeforeEach
  public void init() {

    HashMap<DayOfWeek, Day> weekMap = new HashMap<>();
    Day tuesday = new Day(DayOfWeek.TUESDAY);
    Day wednesday = new Day(DayOfWeek.WEDNESDAY);
    Day thursday = new Day(DayOfWeek.THURSDAY);
    Day friday = new Day(DayOfWeek.FRIDAY);
    Day saturday = new Day(DayOfWeek.SATURDAY);
    Day sunday = new Day(DayOfWeek.SUNDAY);
    weekMap.put(DayOfWeek.MONDAY, monday);
    weekMap.put(DayOfWeek.TUESDAY, tuesday);
    weekMap.put(DayOfWeek.WEDNESDAY, wednesday);
    weekMap.put(DayOfWeek.THURSDAY, thursday);
    weekMap.put(DayOfWeek.FRIDAY, friday);
    weekMap.put(DayOfWeek.SATURDAY, saturday);
    weekMap.put(DayOfWeek.SUNDAY, sunday);

    week = new Week("Suffering", weekMap, 1, 2);
  }

  @Test
  public void getTitle() {
    assertEquals("Suffering", week.getTitle());
    week.setTitle("Last Week");
    assertEquals("Last Week", week.getTitle());
  }

  @Test
  public void getMaxes() {
    assertEquals(1, week.getMaxTasks());
    assertEquals(2, week.getMaxEvents());

    week.setMaxEvents(5);
    week.setMaxTasks(10);

    assertEquals(10, week.getMaxTasks());
    assertEquals(5, week.getMaxEvents());
  }

  @Test
  public void getTotals() {
    assertEquals(0, week.getTotalEvents());
    assertEquals(0, week.getTotalTasks());
    assertEquals(0, week.getTotalCompleted());
  }

  @Test
  public void addTasks() {
    Task task = new Task("Do homework", DayOfWeek.FRIDAY, "OOD", false);
    Task noDesc = new Task("example", DayOfWeek.MONDAY, "", true);

    week.addTask(task, DayOfWeek.MONDAY);
    assertEquals(1, week.getTotalTasks());
    week.addTask(noDesc, DayOfWeek.TUESDAY);
    assertEquals(2, week.getTotalTasks());
  }

  @Test
  public void addEvents() {
    Event event = new Event("OOD", DayOfWeek.MONDAY, "lecture",
        "1.4 hours", "11:40AM");
    week.addEvent(event, DayOfWeek.MONDAY);
    assertEquals(1, week.getTotalEvents());
  }

  @Test
  public void getDay() {
    assertEquals(monday, week.getDay(DayOfWeek.MONDAY));
  }
}
