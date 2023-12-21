package cs3500.pa05.model;

import java.time.DayOfWeek;

public class Task extends AbstractItem {
  private boolean complete;

  /**
   * Constructs a task with a description
   *
   * @param name name of task
   * @param dayName day of week
   * @param description details of the task
   */
  public Task(String name, DayOfWeek dayName, String description, boolean complete) {
    super(name, dayName, description);
    this.complete = complete;
  }

  /**
   * Returns the status of the task
   *
   * @return if it is complete
   */
  public boolean isComplete() {
    return this.complete;
  }

  /**
   * Marks the task as complete
   *
   * @param complete the status
   */
  public void setComplete(boolean complete) {
    this.complete = complete;
  }

  public String toString() {
    String completed;
    if (complete) {
      completed = "Yes";
    } else {
      completed = "No";
    }
    if (description.length() != 0) {
      return name + System.lineSeparator() + description + System.lineSeparator()
          + "Done: " + completed;
    } else {
      return name + System.lineSeparator() + "Done: " + completed;
    }
  }
}
