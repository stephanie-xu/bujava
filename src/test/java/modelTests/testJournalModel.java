package modelTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa05.model.Day;
import cs3500.pa05.model.JournalModel;
import cs3500.pa05.model.Theme;
import cs3500.pa05.model.Week;
import java.time.DayOfWeek;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * tests journal model class
 */
public class testJournalModel {

  Week week;
  JournalModel journal;

  /**
   * init method for week and journal
   */
  @BeforeEach
  public void init() {
    HashMap<DayOfWeek, Day> weekMap = new HashMap<>();
    Day monday = new Day(DayOfWeek.MONDAY);
    Day tuesday = new Day(DayOfWeek.TUESDAY);
    Day wednesday = new Day(DayOfWeek.WEDNESDAY);
    Day thursday = new Day(DayOfWeek.THURSDAY);
    Day friday = new Day(DayOfWeek.FRIDAY);
    Day saturday = new Day(DayOfWeek.SATURDAY);
    Day sunday = new Day(DayOfWeek.SUNDAY);
    weekMap.put(DayOfWeek.MONDAY, monday);
    weekMap.put(DayOfWeek.TUESDAY, tuesday);
    weekMap.put(DayOfWeek.WEDNESDAY, wednesday);
    weekMap.put(DayOfWeek.THURSDAY, thursday);
    weekMap.put(DayOfWeek.FRIDAY, friday);
    weekMap.put(DayOfWeek.SATURDAY, saturday);
    weekMap.put(DayOfWeek.SUNDAY, sunday);

    week = new Week("Suffering", weekMap, 1, 2);
    journal = new JournalModel();
  }

  /**
   * Tests getting themes, week, notes
   */
  @Test
  public void testWeek() {
    assertEquals(null, journal.getWeek());
    assertEquals(Theme.DEFAULT, journal.getTheme());
    assertEquals("", journal.getNotes());
  }

  /**
   * tests setting new week
   */
  @Test
  public void testSetWeek() {
    journal.setWeek(week);
    assertEquals(week, journal.getWeek());
  }

  /**
   * tests changing theme
   */
  @Test
  public void testSetTheme() {
    journal.setTheme(Theme.GREEN);
    assertEquals(Theme.GREEN, journal.getTheme());
    journal.setTheme(Theme.LIGHT);
    assertEquals(Theme.LIGHT, journal.getTheme());
  }

  /**
   * tests adding notes
   */
  @Test
  public void testSetNotes() {
    journal.setNotes("new notes");
    assertEquals("new notes", journal.getNotes());
  }
}
