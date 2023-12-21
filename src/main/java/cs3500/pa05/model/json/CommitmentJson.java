package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.Day;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.Week;
import java.util.ArrayList;

public class CommitmentJson {
  @JsonProperty("maxEvents")
  private int maxEvents;
  @JsonProperty("maxTasks")
  private int maxTasks;

  public CommitmentJson(Week week) {
    this.maxEvents = week.getMaxEvents();
    this.maxTasks = week.getMaxTasks();
  }

  public CommitmentJson() {
  }

  public int maxEvents() {
    return maxEvents;
  }

  public int maxTasks() {
    return maxTasks;
  }
}