package cs3500.pa05.model;

import java.time.DayOfWeek;

/**
 * Represents an event in the bullet journal
 */
public class Event extends AbstractItem {
  private String length;
  private String start;

  /**
   * Constructs an event with a description
   *
   * @param name name of event
   * @param dayName day of the week
   * @param description the details of the event
   * @param length duration of the event
   * @param start the time the event starts
   */
  public Event(String name, DayOfWeek dayName, String description, String length,
               String start) {
    super(name, dayName, description);
    this.length = length;
    this.start = start;
  }

  /**
   * Returns the length of the event
   *
   * @return how long it lasts
   */
  public String getLength() {
    return length;
  }

  /**
   * Returns the start time of the event
   *
   * @return when it starts
   */
  public String getStart() {
    return start;
  }

  /**
   * Changes the length of the event
   *
   * @param length the new length
   */
  public void setLength(String length) {
    this.length = length;
  }


  /**
   * Changes the start of the event
   *
   * @param start the new start
   */
  public void setStart(String start) {
    this.start = start;
  }

  /**
   * Turns the event data into a string
   *
   * @return a string representation
   */
  public String toString() {
    if (description.length() != 0) {
      return name + System.lineSeparator() + description + System.lineSeparator()
          + "Start: " + start + System.lineSeparator() + "Length: " + length;
    } else {
      return name + System.lineSeparator() + "Start: " + start
          + System.lineSeparator() + "Length: " + length;
    }
  }
}
