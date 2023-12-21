package cs3500.pa05.controller;

import cs3500.pa05.model.JournalModel;
import cs3500.pa05.model.Task;
import java.io.IOException;
import java.time.DayOfWeek;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Controller for making changes to a task
 */
public class EditTaskController extends AbstractSecondaryController {
  private Task task;
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
  private CheckBox done;
  @FXML
  private Button delete;
  @FXML
  private Button ok;
  @FXML
  private Label warning;

  public EditTaskController(Stage stage, JournalModel journal, WeekController mainController,
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
   * @param task the info
   */
  public void set(Task task) {
    this.task = task;
    this.previousChosenDay = task.getDayName();
    this.chosenDay = task.getDayName();
    String day = task.getDayName().toString().toLowerCase();
    this.dayOfWeek.setValue(day.substring(0, 1).toUpperCase() + day.substring(1));
    this.name.setText(task.getName());
    this.description.setText(task.getDescription());
    this.done.setSelected(task.isComplete());
    this.warning.setText("");
    ArrayList<Hyperlink> createdLinks = showLinks(task.getDescription());
    links.getChildren().addAll(createdLinks);
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
    this.journal.getWeek().getDay(this.previousChosenDay).removeTask(this.task);
    stage.setScene(this.mainScene);
    this.mainController.update();
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
    String description = this.description.getText();
    this.task.setName(name);
    this.task.setDayName(this.chosenDay);
    this.task.setDescription(description);
    this.task.setComplete(this.done.isSelected());
    if (previousChosenDay != chosenDay) {
      this.journal.getWeek().getDay(this.previousChosenDay).removeTask(this.task);
      this.journal.getWeek().getDay(this.chosenDay).addTask(this.task);
    }
    links.getChildren().clear();
    stage.setScene(this.mainScene);
    this.mainController.update();
  }
}