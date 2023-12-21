package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.Task;

public class TaskJson {
  @JsonProperty("name")
  private String name;
  @JsonProperty("description")
  private String description;
  @JsonProperty("isDone")
  private boolean isDone;

  public TaskJson(Task task) {
    this.name = task.getName();
    this.description = task.getDescription();
    this.isDone = task.isComplete();
  }

  @JsonCreator
  public TaskJson() {
  }

  public String name() {
    return name;
  }

  public String description() {
    return description;
  }

  @JsonIgnore
  public boolean isDone() {
    return isDone;
  }
}
