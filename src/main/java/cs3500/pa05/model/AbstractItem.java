package cs3500.pa05.model;

import java.time.DayOfWeek;

/**
 * Abstract class with methods for bullet journal items
 */
public abstract class AbstractItem implements Item {
  protected String name;
  protected DayOfWeek dayName;
  protected String description;

  protected AbstractItem(String name, DayOfWeek dayName, String description) {
    this.name = name;
    this.dayName = dayName;
    this.description = description;
  }

  public String getName() {
    return name;
  }

  public DayOfWeek getDayName() {
    return dayName;
  }

  public String getDescription() {
    return description;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDayName(DayOfWeek dayName) {
    this.dayName = dayName;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
