package cs3500.pa05.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.JournalModel;

public class JournalJson {
  @JsonProperty("week")
  private WeekJson week;
  @JsonProperty("theme")
  private String theme;
  @JsonProperty("quotesNotes")
  private String quotesNotes;

  public JournalJson(JournalModel journal) {
    this.week = new WeekJson(journal.getWeek());
    this.theme = String.valueOf(journal.getTheme());
    this.quotesNotes = journal.getNotes();
  }

  public JournalJson() {
  }

  public WeekJson week() {
    return week;
  }

  public String theme() {
    return theme;
  }

  public String quotesNotes() {
    return quotesNotes;
  }
}
