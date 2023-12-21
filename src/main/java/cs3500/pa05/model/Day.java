package cs3500.pa05.model;
import java.time.DayOfWeek;
import java.util.ArrayList;
import javafx.scene.control.Hyperlink;

public class Day {
  private ArrayList<Event> events;
  private ArrayList<Task> tasks;
  private DayOfWeek dayOfWeek;
  private ArrayList<Hyperlink> links;

  /**
   * Constructs a new day representation from the day passed in
   *
   * @param day the day of the week
   */
  public Day(DayOfWeek day) {
    this.events = new ArrayList<>();
    this.tasks = new ArrayList<>();
    this.dayOfWeek = day;
    this.links = new ArrayList<>();
  }

  public Day(ArrayList<Event> events, ArrayList<Task> tasks, DayOfWeek day) {
    this.events = events;
    this.tasks = tasks;
    this.dayOfWeek = day;
    this.links = new ArrayList<>();
  }

  public ArrayList<Event> getEvents() {
    return events;
  }

  public ArrayList<Task> getTasks() {
    return tasks;
  }

  public ArrayList<Hyperlink> getLinks() {
    return links;
  }

  public void addEvent(Event event) {
    this.events.add(event);
  }

  public void addTask(Task task) {
    this.tasks.add(task);
  }

  public void removeEvent(Event event) {
    this.events.remove(event);
  }

  public void removeTask(Task task) {
    this.tasks.remove(task);
  }

  /**
   * Counts all the events in a day
   *
   * @return the number of tasks
   */
  public int totalEvents() {
    return this.events.size();
  }

  /**
   * Counts the tasks that have been completed
   *
   * @return the number of tasks
   */
  public int numCompleted() {
    int count = 0;
    for (Task t : tasks) {
      if (t.isComplete()) {
        count += 1;
      }
    }
    return count;
  }

  /**
   * Counts all the tasks in a day
   *
   * @return the number of tasks
   */
  public int totalTasks() {
    return this.tasks.size();
  }

  /**
   * Calculates the percentage of completed tasks
   *
   * @return the percentage of tasks
   */
  public double percentCompleted() {
    if (this.totalTasks() == 0) {
      return 1.0;
    } else {
      return (double) this.numCompleted() / (double) this.totalTasks();
    }
  }

  public int remainingTasks() {
    return this.totalTasks() - this.numCompleted();
  }
}
