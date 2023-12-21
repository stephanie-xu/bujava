package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.Day;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.Task;
import java.util.ArrayList;

public class DayJson {
  @JsonProperty("events")
  private ArrayList<EventJson> events;
  @JsonProperty("tasks")
  private ArrayList<TaskJson> tasks;

  public DayJson(Day day) {
    events = new ArrayList<>();
    tasks = new ArrayList<>();
    ArrayList<Event> dayEvents = day.getEvents();
    ArrayList<Task> dayTasks = day.getTasks();
    for (Event event : dayEvents) {
      events.add(new EventJson(event));
    }
    for (Task task : dayTasks) {
      tasks.add(new TaskJson(task));
    }
  }

  @JsonCreator
  public DayJson() {
  }

  public ArrayList<EventJson> events() {
    return events;
  }

  public ArrayList<TaskJson> tasks() {
    return tasks;
  }
}