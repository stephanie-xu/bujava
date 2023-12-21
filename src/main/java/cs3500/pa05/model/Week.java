package cs3500.pa05.model;

import java.time.DayOfWeek;
import java.util.HashMap;

/**
 * Represents a week in a bullet journal
 */
public class Week {
  private String title;
  private HashMap<DayOfWeek, Day> days;
  private int maxTasks;
  private int maxEvents;

  /**
   * Creates a week
   *
   * @param title name of the week
   * @param days the days in the week
   * @param maxTasks the limit on tasks
   * @param maxEvents the limit on events
   */
  public Week(String title, HashMap<DayOfWeek, Day> days, int maxTasks, int maxEvents) {
    this.title = title;
    this.days = days;
    this.maxTasks = maxTasks;
    this.maxEvents = maxEvents;
  }

  /**
   * Retrieves the title of the week
   *
   * @return the title
   */
  public String getTitle() {
    return this.title;
  }

  /**
   * Returns the maximum number of tasks
   *
   * @return the maximum number of tasks
   */
  public int getMaxTasks() {
    return this.maxTasks;
  }

  /**
   * Returns the maximum number of events
   *
   * @return the maximum number of events
   */
  public int getMaxEvents() {
    return this.maxEvents;
  }

  /**
   * Returns the given day from the day of the week enum from hashmap
   *
   * @param day a day of the week
   * @return the day
   */
  public Day getDay(DayOfWeek day) {
    return this.days.get(day);
  }

  public int getTotalEvents() {
    int result = 0;
    for (DayOfWeek day : this.days.keySet()) {
      result += this.days.get(day).totalEvents();
    }
    return result;
  }

  public int getTotalTasks() {
    int result = 0;
    for (DayOfWeek day : this.days.keySet()) {
      result += this.days.get(day).totalTasks();
    }
    return result;
  }

  public int getTotalCompleted() {
    int result = 0;
    for (DayOfWeek day : this.days.keySet()) {
      result += this.days.get(day).numCompleted();
    }
    return result;
  }

  public void addEvent(Event event, DayOfWeek day) {
    days.get(day).addEvent(event);
  }

  public void addTask(Task task, DayOfWeek day) {
    days.get(day).addTask(task);
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setMaxEvents(int n) {
    this.maxEvents = n;
  }

  public void setMaxTasks(int n) {
    this.maxTasks = n;
  }
}
