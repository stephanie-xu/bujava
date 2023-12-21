package cs3500.pa05.controller;

import cs3500.pa05.model.Event;
import cs3500.pa05.model.JournalModel;
import cs3500.pa05.view.View;
import java.io.IOException;
import java.time.DayOfWeek;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Controller for making changes to an Event
 */
public class EditEventController extends AbstractSecondaryController {
  private Event event;
  private View scene;
  private DayOfWeek previousChosenDay;
  @FXML
  private ComboBox<String> dayOfWeek;
  @FXML
  private HBox links;
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
  private Button delete;
  @FXML
  private Button ok;
  @FXML
  private Label warning;

  public EditEventController(Stage stage, JournalModel journal, WeekController mainController,
                            Scene mainScene) {
    super(stage, journal, mainController, mainScene);
  }

  /**
   * Assigns event handlers to buttons
   */
  public void init() {
    dayOfWeek.setOnAction(event -> dayOfWeek());
    delete.setOnAction(event -> delete());
    ok.setOnAction(event -> ok());
  }

  /**
   * Populates the info in the scene
   *
   * @param event the info
   */
  public void set(Event event) {
    this.event = event;
    this.previousChosenDay = event.getDayName();
    this.chosenDay = event.getDayName();
    String day = event.getDayName().toString().toLowerCase();
    this.dayOfWeek.setValue(day.substring(0, 1).toUpperCase() + day.substring(1));
    this.name.setText(event.getName());
    this.description.setText(event.getDescription());
    this.startTime.setText(event.getStart());
    this.duration.setText(event.getLength());
    this.warning.setText("");
    ArrayList<Hyperlink> createdLinks = showLinks(event.getDescription());
    links.getChildren().addAll(createdLinks);
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

  private void delete() {
    links.getChildren().clear();
    this.journal.getWeek().getDay(this.previousChosenDay).removeEvent(this.event);
    stage.setScene(this.mainScene);
    this.mainController.update();
  }

  private ArrayList<Hyperlink> showLinks(String description) {
    ArrayList<Hyperlink> hyperlinks = new ArrayList<>();
    String[] parts = description.split(" ");

    for (String part : parts) {
      if (part.contains("http://") || part.contains("https://")) {
        Hyperlink link = new Hyperlink(part);
        link.setOnAction(event1 -> {
          try {
            java.awt.Desktop.getDesktop().browse(java.net.URI.create(part));
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
        });
        hyperlinks.add(link);
      }
    }
    return hyperlinks;
  }

  private void ok() {
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
    this.event.setName(name);
    this.event.setDayName(this.chosenDay);
    this.event.setDescription(description);
    this.event.setLength(duration);
    this.event.setStart(startTime);
    if (previousChosenDay != chosenDay) {
      this.journal.getWeek().getDay(this.previousChosenDay).removeEvent(this.event);
      this.journal.getWeek().getDay(this.chosenDay).addEvent(this.event);
    }
    links.getChildren().clear();
    stage.setScene(this.mainScene);
    this.mainController.update();
  }
}