package cs3500.pa05.controller;

import cs3500.pa05.model.Event;
import cs3500.pa05.model.JournalModel;
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
 * Controller for adding events to the bullet journal
 */
public class AddEventController extends AbstractSecondaryController {
  @FXML
  private ComboBox<String> dayOfWeek;
  private DayOfWeek chosenDay = null;
  @FXML
  private TextField name;
  @FXML
  private TextArea description;
  @FXML
  private TextField startTime;
  @FXML
  private TextField duration;
  @FXML
  private Button cancel;
  @FXML
  private Button add;
  @FXML
  private Label warning;


  public AddEventController(Stage stage, JournalModel journal, WeekController mainController,
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
    this.startTime.clear();
    this.duration.clear();
    this.warning.setText("");
  }

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
    String startTime = this.startTime.getText();
    String[] startTimeSplit = startTime.split(":");
    if (startTimeSplit.length != 2) {
      this.warning.setText("Make sure start time is formatted as hr:min");
      return;
    }
    try {
      int startTimeHr = Integer.parseInt(startTimeSplit[0]);
      int startTimeMin = Integer.parseInt(startTimeSplit[1]);
      if (startTimeHr < 0 || startTimeHr > 23) {
        this.warning.setText("Make sure start time hour is between 0 and 23");
        return;
      } else if (startTimeMin < 0 || startTimeMin > 59) {
        this.warning.setText("Make sure start time minute is between 0 and 59");
        return;
      }
      if (startTimeMin < 10) {
        startTime = startTime.substring(0, startTime.indexOf(":") + 1);
        startTime += "0" + startTimeMin;
      }
    } catch (NumberFormatException e) {
      this.warning.setText("Make sure start time hour and minute are numbers");
      return;
    }
    String duration = this.duration.getText();
    String[] durationSplit = duration.split(":");
    if (durationSplit.length != 2) {
      this.warning.setText("Make sure duration is formatted as hr:min");
      return;
    }
    try {
      int durationHr = Integer.parseInt(durationSplit[0]);
      int durationMin = Integer.parseInt(durationSplit[1]);
      if (durationHr < 0 || durationHr > 23) {
        this.warning.setText("Make sure duration hour is between 0 and 23");
        return;
      } else if (durationMin < 0 || durationMin > 59) {
        this.warning.setText("Make sure duration minute is between 0 and 59");
        return;
      }
      if (durationMin < 10) {
        duration = duration.substring(0, duration.indexOf(":") + 1);
        duration += "0" + durationMin;
      }
    } catch (NumberFormatException e) {
      this.warning.setText("Make sure duration hour and minute are numbers");
      return;
    }
    String description = this.description.getText();
    Event event = new Event(name, this.chosenDay, description, duration, startTime);
    this.journal.getWeek().addEvent(event, this.chosenDay);
    stage.setScene(this.mainScene);
    this.mainController.update();
  }
}
