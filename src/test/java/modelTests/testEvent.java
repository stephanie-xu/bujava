package modelTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.model.Event;
import java.time.DayOfWeek;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for event class
 */
public class testEvent {

  Event event;

  /**
   * Initialization method
   */
  @BeforeEach
  public void init() {
    event = new Event("OOD", DayOfWeek.MONDAY, "lecture",
        "1.4 hours", "11:40AM");
  }

  /**
   * Tests getters/setters
   */
  @Test
  public void setTimes() {
    assertEquals("1.4 hours", event.getLength());
    assertEquals("11:40AM", event.getStart());
    event.setStart("11:50AM");
    event.setLength("1 hour");
    assertEquals("1 hour", event.getLength());
    assertEquals("11:50AM", event.getStart());
  }

  /**
   * Test for to string
   */
  @Test
  public void testToString() {
    Event noDesc = new Event("OOD", DayOfWeek.MONDAY, "",
        "1.4 hours", "11:40AM");
    assertEquals("OOD\nlecture\nStart: 11:40AM\nLength: 1.4 hours", event.toString());
    assertEquals("OOD\nStart: 11:40AM\nLength: 1.4 hours", noDesc.toString());
  }

  @Test
  public void testDescription() {
    assertEquals("lecture", event.getDescription());
    event.setDescription("shortened lecture");
    assertEquals("shortened lecture", event.getDescription());
  }

  @Test
  public void testName() {
    assertEquals("OOD", event.getName());
    event.setName("CS3500");
    assertEquals("CS3500", event.getName());
  }

  @Test
  public void testDayName() {
    assertEquals(DayOfWeek.MONDAY, event.getDayName());
    event.setDayName(DayOfWeek.TUESDAY);
    assertEquals(DayOfWeek.TUESDAY, event.getDayName());
  }
}
