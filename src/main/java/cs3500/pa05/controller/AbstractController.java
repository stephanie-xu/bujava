package cs3500.pa05.controller;

import cs3500.pa05.model.JournalModel;
import javafx.stage.Stage;

/**
 * Abstract Controller for main logic components
 */
public abstract class AbstractController implements Controller {
  protected Stage stage;
  protected final JournalModel journal;

  AbstractController(Stage stage, JournalModel journal) {
    this.stage = stage;
    this.journal = journal;
  }

  public abstract void init();
}
