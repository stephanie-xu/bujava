package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.Event;

public class EventJson {
  @JsonProperty("name")
  private String name;
  @JsonProperty("description")
  private String description;
  @JsonProperty("length")
  private String length;
  @JsonProperty("start")
  private String start;

  public EventJson(Event event) {
    this.name = event.getName();
    this.description = event.getDescription();
    this.length = event.getLength();
    this.start = event.getStart();
  }

  @JsonCreator
  public EventJson() {
  }

  public String name() {
    return name;
  }

  public String description() {
    return description;
  }

  public String length() {
    return length;
  }

  public String start() {
    return start;
  }
}
