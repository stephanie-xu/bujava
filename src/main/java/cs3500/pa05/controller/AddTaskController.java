package cs3500.pa05.controller;

import cs3500.pa05.model.JournalModel;
import cs3500.pa05.model.Task;
import java.time.DayOfWeek;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller for adding a task to the bullet journal
 */
public class AddTaskController extends AbstractSecondaryController {
  @FXML
  private ComboBox<String> dayOfWeek;
  private DayOfWeek chosenDay = null;
  @FXML
  private TextField name;
  @FXML
  private TextArea description;
  @FXML
  private Button cancel;
  @FXML
  private Button add;
  @FXML
  private Label warning;

  public AddTaskController(Stage stage, JournalModel journal, WeekController mainController,
                           Scene mainScene) {
    super(stage, journal, mainController, mainScene);
  }

  /**
   * Assigns event handlers to buttons
   */
  public void init() {
    dayOfWeek.setOnAction(event -> dayOfWeek());
    cancel.setOnAction(event -> stage.setScene(this.mainScene));
    add.setOnAction(event -> add());
  }


  /**
   * Removes info from the fields
   */
  public void clear() {
    this.name.clear();
    this.description.clear();
    this.warning.setText("");
  }

  /**
   * Assigns the days to the days of the week
   */
  private void dayOfWeek() {
    String selectedItem = dayOfWeek.getSelectionModel().getSelectedItem();
    switch (selectedItem) {
      case "Sunday" -> this.chosenDay = DayOfWeek.SUNDAY;
      case "Monday" -> this.chosenDay = DayOfWeek.MONDAY;
      case "Tuesday" -> this.chosenDay = DayOfWeek.TUESDAY;
      case "Wednesday" -> this.chosenDay = DayOfWeek.WEDNESDAY;
      case "Thursday" -> this.chosenDay = DayOfWeek.THURSDAY;
      case "Friday" -> this.chosenDay = DayOfWeek.FRIDAY;
      case "Saturday" -> this.chosenDay = DayOfWeek.SATURDAY;
    }
  }

  private void add() {
    if (this.chosenDay == null) {
      this.warning.setText("Please choose a day for the event");
      return;
    }
    String name = this.name.getText();
    if (name.length() == 0) {
      this.warning.setText("Please enter a name for the event");
      return;
    }
    String description = this.description.getText();
    Task task = new Task(name, this.chosenDay, description, false);
    this.journal.getWeek().addTask(task, this.chosenDay);
    stage.setScene(this.mainScene);
    this.mainController.update();
  }
}