package cs3500.pa05.model;

/**
 * Represents a bullet journal container
 */
public class JournalModel {
  private Week week;
  private Theme theme;
  private String notes;

  /**
   * Creates a bullet journal container
   */
  public JournalModel() {
    this.week = null;
    this.theme = Theme.DEFAULT;
    this.notes = "";
  }

  /**
   * Fetches the week of the journal container
   *
   * @return the week
   */
  public Week getWeek() {
    return this.week;
  }

  /**
   * Returns the current theme
   *
   * @return the theme
   */
  public Theme getTheme() {
    return this.theme;
  }

  /**
   * Returns the notes sidebar
   *
   * @return the notes
   */
  public String getNotes() {
    return this.notes;
  }

  /**
   * Sets the week of the journal container
   *
   * @param week the new week
   */
  public void setWeek(Week week) {
    this.week = week;
  }

  /**
   * Sets the theme of the journal container
   *
   * @param theme the new theme
   */
  public void setTheme(Theme theme) {
    this.theme = theme;
  }

  /**
   * Sets the notes sidebar
   *
   * @param notes the new notes
   */
  public void setNotes(String notes) {
    this.notes = notes;
  }
}
