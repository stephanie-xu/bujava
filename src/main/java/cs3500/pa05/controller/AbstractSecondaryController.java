package cs3500.pa05.controller;

import cs3500.pa05.model.JournalModel;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Abstract controller for handling secondary jobs, like adding/editing items
 */
public abstract class AbstractSecondaryController extends AbstractController {
  protected final Scene mainScene;
  protected final WeekController mainController;
  AbstractSecondaryController(Stage stage, JournalModel journal, WeekController mainController,
                              Scene mainScene) {
    super(stage, journal);
    this.mainController = mainController;
    this.mainScene = mainScene;
  }
}
