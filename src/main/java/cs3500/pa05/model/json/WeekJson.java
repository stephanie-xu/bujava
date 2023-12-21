package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.Week;
import java.time.DayOfWeek;

public class WeekJson {
  @JsonProperty("title")
  private String title;
  @JsonProperty("sunday")
  private DayJson sunday;
  @JsonProperty("monday")
  private DayJson monday;
  @JsonProperty("tuesday")
  private DayJson tuesday;
  @JsonProperty("wednesday")
  private DayJson wednesday;
  @JsonProperty("thursday")
  private DayJson thursday;
  @JsonProperty("friday")
  private DayJson friday;
  @JsonProperty("saturday")
  private DayJson saturday;
  @JsonProperty("commitment")
  private CommitmentJson commitment;

  public WeekJson(Week week) {
    this.title = week.getTitle();
    this.sunday = new DayJson(week.getDay(DayOfWeek.SUNDAY));
    this.monday = new DayJson(week.getDay(DayOfWeek.MONDAY));
    this.tuesday = new DayJson(week.getDay(DayOfWeek.TUESDAY));
    this.wednesday = new DayJson(week.getDay(DayOfWeek.WEDNESDAY));
    this.thursday = new DayJson(week.getDay(DayOfWeek.THURSDAY));
    this.friday = new DayJson(week.getDay(DayOfWeek.FRIDAY));
    this.saturday = new DayJson(week.getDay(DayOfWeek.SATURDAY));
    this.commitment = new CommitmentJson(week);
  }

  @JsonCreator
  public WeekJson() {
  }

  public String title() {
    return title;
  }

  public DayJson sunday() {
    return sunday;
  }

  public DayJson monday() {
    return monday;
  }

  public DayJson tuesday() {
    return tuesday;
  }

  public DayJson wednesday() {
    return wednesday;
  }

  public DayJson thursday() {
    return thursday;
  }

  public DayJson friday() {
    return friday;
  }

  public DayJson saturday() {
    return saturday;
  }

  public CommitmentJson commitment() {
    return commitment;
  }
}