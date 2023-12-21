package cs3500.pa05.model;

import java.time.DayOfWeek;

/**
 * Interface for each object that a bullet journal keeps track of
 */
public interface Item {
  /**
   * Returns the name of the item
   *
   * @return the name
   */
  String getName();

  /**
   * Returns the day of the week in which the item is assigned
   *
   * @return the day of the week
   */
  DayOfWeek getDayName();

  /**
   * Returns the description of the item
   *
   * @return a textual description entered by the user
   */
  String getDescription();

  /**
   * Sets the name of the item
   *
   * @param name name provided by the user
   */
  void setName(String name);

  /**
   * Sets the day of the week of the item
   *
   * @param dayName day selected by user
   */
  void setDayName(DayOfWeek dayName);

  /**
   * Sets the description of the item
   *
   * @param description description entered by user
   */
  void setDescription(String description);
}
