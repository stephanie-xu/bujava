package cs3500.pa05.controller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa05.model.json.CommitmentJson;
import cs3500.pa05.model.json.DayJson;
import cs3500.pa05.model.json.EventJson;
import cs3500.pa05.model.json.JournalJson;
import cs3500.pa05.model.json.TaskJson;
import cs3500.pa05.model.json.WeekJson;
import cs3500.pa05.model.Day;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.JournalModel;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.Theme;
import cs3500.pa05.model.Week;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.kordamp.ikonli.javafx.FontIcon;

/**
 * Controller for a week view
 */
public class WeekController extends AbstractController {
  private File bujoFile = null;
  private Scene addEventScene;
  private Scene addTaskScene;
  private Scene editEventScene;
  private Scene editTaskScene;
  private AddEventController addEventController;
  private AddTaskController addTaskController;
  private EditEventController editEventController;
  private EditTaskController editTaskController;
  @FXML
  private ListView<Task> taskList;
  @FXML
  private Button openFile;
  @FXML
  private Button saveToFile;
  @FXML
  private TextField title;
  @FXML
  private MenuButton add;
  @FXML
  private MenuItem addEvent;
  @FXML
  private MenuItem addTask;
  @FXML
  private MenuButton theme;
  @FXML
  private MenuItem light;
  @FXML
  private MenuItem dark;
  @FXML
  private MenuItem green;
  @FXML
  private MenuItem defaultTheme;
  @FXML
  private ListView<Event> sundayEvents;
  @FXML
  private ListView<Event> mondayEvents;
  @FXML
  private ListView<Event> tuesdayEvents;
  @FXML
  private ListView<Event> wednesdayEvents;
  @FXML
  private ListView<Event> thursdayEvents;
  @FXML
  private ListView<Event> fridayEvents;
  @FXML
  private ListView<Event> saturdayEvents;
  @FXML
  private ProgressBar sundayProgress;
  @FXML
  private ProgressBar mondayProgress;
  @FXML
  private ProgressBar tuesdayProgress;
  @FXML
  private ProgressBar wednesdayProgress;
  @FXML
  private ProgressBar thursdayProgress;
  @FXML
  private ProgressBar fridayProgress;
  @FXML
  private ProgressBar saturdayProgress;
  @FXML
  private Label sundayRemaining;
  @FXML
  private Label mondayRemaining;
  @FXML
  private Label tuesdayRemaining;
  @FXML
  private Label wednesdayRemaining;
  @FXML
  private Label thursdayRemaining;
  @FXML
  private Label fridayRemaining;
  @FXML
  private Label saturdayRemaining;
  @FXML
  private ListView<Task> sundayTasks;
  @FXML
  private ListView<Task> mondayTasks;
  @FXML
  private ListView<Task> tuesdayTasks;
  @FXML
  private ListView<Task> wednesdayTasks;
  @FXML
  private ListView<Task> thursdayTasks;
  @FXML
  private ListView<Task> fridayTasks;
  @FXML
  private ListView<Task> saturdayTasks;
  @FXML
  private Label totalEvents;
  @FXML
  private Label totalTasks;
  @FXML
  private Label completedTasks;
  @FXML
  private TextField maxEvents;
  @FXML
  private TextField maxTasks;
  @FXML
  private Label maxEventsWarning;
  @FXML
  private Label maxTasksWarning;
  @FXML
  private TextArea notes;

  public WeekController(Stage stage, JournalModel journal) {
    super(stage, journal);
  }

  public void setAddEventScene(Scene addEventScene) {
    this.addEventScene = addEventScene;
  }

  public void setAddTaskScene(Scene addTaskScene) {
    this.addTaskScene = addTaskScene;
  }

  public void setEditEventScene(Scene editEventScene) {
    this.editEventScene = editEventScene;
  }

  public void setEditTaskScene(Scene editTaskScene) {
    this.editTaskScene = editTaskScene;
  }

  public void setAddEventController(AddEventController addEventController) {
    this.addEventController = addEventController;
  }

  public void setAddTaskController(AddTaskController addTaskController) {
    this.addTaskController = addTaskController;
  }

  public void setEditEventController(EditEventController editEventController) {
    this.editEventController = editEventController;
  }

  public void setEditTaskController(EditTaskController editTaskController) {
    this.editTaskController = editTaskController;
  }

  /**
   * Links the file open and file save buttons to their respective methods
   */
  public void init() {
    this.openFile.setOnAction(event -> openBujoFile());
    this.saveToFile.setOnAction(event -> saveToBujoFile());
    this.title.setOnAction(event -> saveTitle());
    this.maxEvents.setOnAction(event -> saveMaxEvents());
    this.maxTasks.setOnAction(event -> saveMaxTasks());
    this.notes.textProperty().addListener(event -> saveNotes());
    this.addEvent.setOnAction(event -> addEvent());
    this.addTask.setOnAction(event -> addTask());
    this.green.setOnAction(event -> setGreenTheme());
    this.light.setOnAction(event -> setLightTheme());
    this.dark.setOnAction(event -> setDarkTheme());
    this.defaultTheme.setOnAction(event -> setDefaultTheme());
  }

  /**
   * Syncs the events and tasks lists in the view to the info stores in the journal
   */
  public void update() {
    this.title.setText(this.journal.getWeek().getTitle());
    List<DayOfWeek> daysOfWeek = Arrays.asList(DayOfWeek.SUNDAY, DayOfWeek.MONDAY,
        DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY,
        DayOfWeek.SATURDAY);
    this.updateEvents(daysOfWeek);
    this.updateTasks(daysOfWeek);
    this.updateWeeklyOverviewCommitmentsAndNotes();
    this.updateTheme();
  }

  /**
   * Updates the events shown on each day from left to right in the order of the given enum list
   *
   * @param daysOfWeek a list with each day in the week
   */
  public void updateEvents(List<DayOfWeek> daysOfWeek) {
    boolean overMaxEvents = false;
    int maxEvents = this.journal.getWeek().getMaxEvents();
    List<ListView<Event>> dayEvents = Arrays.asList(sundayEvents, mondayEvents, tuesdayEvents,
        wednesdayEvents, thursdayEvents, fridayEvents, saturdayEvents);
    for (int i = 0; i < 7; i++) {
      DayOfWeek dayEnum = daysOfWeek.get(i);
      ListView<Event> day = dayEvents.get(i);
      day.getItems().clear();
      ArrayList<Event> events = this.journal.getWeek().getDay(dayEnum).getEvents();
      day.getItems().addAll(events);
      if (events.size() > maxEvents) {
        overMaxEvents = true;
      }
      day.setCellFactory(param -> new EventListCell());
    }
    if (overMaxEvents) {
      this.maxEventsWarning.setText("Warning");
    } else {
      this.maxEventsWarning.setText("");
    }
  }

  public class EventListCell extends ListCell<Event> {
    public EventListCell() {
      setOnMouseClicked(event -> {
        if (event.getClickCount() == 1 && getItem() != null) {
          Event item = getItem();
          stage.setScene(editEventScene);
          editEventController.set(item);
        }
      });
    }

    @Override
    protected void updateItem(Event item, boolean empty) {
      super.updateItem(item, empty);
      if (empty || item == null) {
        setText(null);
      } else {
        setText(item.toString());
      }
    }
  }

  /**
   * Removes the other styles and changes the view to the green theme
   * Sets the theme to green in the journal as well
   */
  public void setGreenTheme() {
    stage.getScene().getStylesheets().removeAll("light.css", "dark.css");
    stage.getScene().getStylesheets().add("green.css");

    // icons
    openFile.setText("");
    openFile.setGraphic(new FontIcon("bi-archive-fill"));
    saveToFile.setText("");
    saveToFile.setGraphic(new FontIcon("bi-archive"));

    addTask.setGraphic(null);
    addTask.setText("TASK");
    addEvent.setGraphic(null);
    addEvent.setText("EVENT");

    add.setGraphic(new FontIcon("bi-plus-square"));
    add.setText("");

    theme.setGraphic(new FontIcon("bi-palette"));
    theme.setText("");

    journal.setTheme(Theme.GREEN);
  }

  /**
   * Removes the other styles and changes the view to the light theme
   * Sets the theme to light in the journal as well
   */
  public void setLightTheme() {
    stage.getScene().getStylesheets().removeAll("green.css", "dark.css");
    stage.getScene().getStylesheets().add("light.css");

    // icons
    openFile.setText("");
    openFile.setGraphic(new FontIcon("bi-arrow-down-circle"));
    saveToFile.setText("");
    saveToFile.setGraphic(new FontIcon("bi-arrow-up-circle"));

    addTask.setGraphic(new FontIcon("bi-pin"));
    addTask.setText("");
    addEvent.setGraphic(new FontIcon("bi-person-square"));
    addEvent.setText("");

    add.setGraphic(new FontIcon("bi-person-plus-fill"));
    add.setText("");

    theme.setGraphic(new FontIcon("bi-palette-fill"));
    theme.setText("");

    journal.setTheme(Theme.LIGHT);
  }

  /**
   * Removes the other styles and changes the view to the dark theme
   * Sets the theme to dark in the journal as well
   */
  public void setDarkTheme() {
    stage.getScene().getStylesheets().removeAll("light.css", "green.css");
    stage.getScene().getStylesheets().add("dark.css");

    // icons
    defaultIcons();


    journal.setTheme(Theme.DARK);
  }

  /**
   * Removes all styles and changes the view to the default theme
   * Sets the theme to default in the journal as well
   */
  public void setDefaultTheme() {
    stage.getScene().getStylesheets().removeAll("light.css", "green.css", "dark.css");

    // icons
    defaultIcons();

    journal.setTheme(Theme.DEFAULT);
  }

  private void defaultIcons() {
    openFile.setText("OPEN FILE");
    openFile.setGraphic(null);
    saveToFile.setText("SAVE TO FILE");
    saveToFile.setGraphic(null);

    addTask.setGraphic(null);
    addTask.setText("TASK");
    addEvent.setGraphic(null);
    addEvent.setText("EVENT");

    add.setGraphic(null);
    add.setText("ADD");

    theme.setGraphic(null);
    theme.setText("THEME");
  }

  /**
   * Updates the tasks and progress bar shown on each day from left to right in the order of
   * the given enum list and also updates what is shown on the weekly task list
   *
   * @param daysOfWeek a list with each day in the week
   */
  public void updateTasks(List<DayOfWeek> daysOfWeek) {
    boolean overMaxTasks = false;
    int maxTasks = this.journal.getWeek().getMaxTasks();
    List<ListView<Task>> dayTasks = Arrays.asList(sundayTasks, mondayTasks, tuesdayTasks,
        wednesdayTasks, thursdayTasks, fridayTasks, saturdayTasks);
    List<ProgressBar> dayProgresses = Arrays.asList(sundayProgress, mondayProgress, tuesdayProgress,
        wednesdayProgress, thursdayProgress, fridayProgress, saturdayProgress);
    List<Label> dayRemainings = Arrays.asList(sundayRemaining, mondayRemaining, tuesdayRemaining,
        wednesdayRemaining, thursdayRemaining, fridayRemaining, saturdayRemaining);
    List<Task> weeklyTasks = new ArrayList<>();
    for (int i = 0; i < 7; i++) {
      DayOfWeek dayEnum = daysOfWeek.get(i);
      ListView<Task> dayTask = dayTasks.get(i);
      ProgressBar dayProgress = dayProgresses.get(i);
      Day day = this.journal.getWeek().getDay(dayEnum);
      dayTask.getItems().clear();
      dayTask.getItems().addAll(day.getTasks());
      dayProgress.setProgress(day.percentCompleted());
      Label dayRemaining = dayRemainings.get(i);
      dayRemaining.setText("Remaining: " + day.remainingTasks());
      weeklyTasks.addAll(day.getTasks());
      if (day.getTasks().size() > maxTasks) {
        overMaxTasks = true;
      }
      dayTask.setCellFactory(param -> new TaskListCell());
    }
    this.taskList.getItems().clear();
    this.taskList.getItems().addAll(weeklyTasks);
    if (overMaxTasks) {
      this.maxTasksWarning.setText("Warning");
    } else {
      this.maxTasksWarning.setText("");
    }
  }

  private class TaskListCell extends ListCell<Task> {
    public TaskListCell() {
      setOnMouseClicked(event -> {
        if (event.getClickCount() == 1 && getItem() != null) {
          Task item = getItem();
          stage.setScene(editTaskScene);
          editTaskController.set(item);
        }
      });
    }

    @Override
    protected void updateItem(Task item, boolean empty) {
      super.updateItem(item, empty);
      if (empty || item == null) {
        setText(null);
      } else {
        setText(item.toString());
      }
    }
  }

  /**
   * Updates the weekly overview, commitments, and notes in the view
   */
  public void updateWeeklyOverviewCommitmentsAndNotes() {
    this.totalEvents.setText(String.valueOf(this.journal.getWeek().getTotalEvents()));
    this.totalTasks.setText(String.valueOf(this.journal.getWeek().getTotalTasks()));
    int totalTasks = this.journal.getWeek().getTotalTasks();
    int completedTasks = this.journal.getWeek().getTotalCompleted();
    double percent = 1;
    if (totalTasks != 0) {
      percent = (double) completedTasks / (double) totalTasks;
    }
    NumberFormat percentFormat = DecimalFormat.getPercentInstance();
    percentFormat.setMinimumFractionDigits(0);
    String formattedPercentage = percentFormat.format(percent);
    this.completedTasks.setText(formattedPercentage);
    this.maxEvents.setText(String.valueOf(this.journal.getWeek().getMaxEvents()));
    this.maxTasks.setText(String.valueOf(this.journal.getWeek().getMaxTasks()));
    this.notes.setText(String.valueOf(this.journal.getNotes()));
  }

  /**
   * Assigns event handlers for theme enums
   */
  public void updateTheme() {
    Theme theme = this.journal.getTheme();
    switch (theme) {
      case LIGHT -> setLightTheme();
      case DARK -> setDarkTheme();
      case GREEN -> setGreenTheme();
      case DEFAULT -> setDefaultTheme();
    }
  }

  /**
   * Asks the user to choose a bujo file and sets this journalmodel to the file's data
   */
  public void openBujoFile() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Open .bujo File");
    File file = fileChooser.showOpenDialog(this.stage);
    if (file != null && file.toString().endsWith(".bujo")) {
      this.bujoFile = file;
      this.getData();
      this.update();
    } else if (file != null) {
      this.popupWarning("Please select a .bujo file");
    }
  }

  // saves what is stored in this journalmodel to the bujo file
  public void saveToBujoFile() {
    if (this.bujoFile == null) {
      this.popupWarning("Please open a .bujo file first");
    } else {
      try {
        Files.write(this.bujoFile.toPath(),
            new ObjectMapper().writeValueAsString(new JournalJson(this.journal)).getBytes());
        this.popupWarning("File saved!");
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }

  private void saveTitle() {
    if (this.bujoFile == null) {
      this.popupWarning("Please open a .bujo file first");
    } else {
      this.journal.getWeek().setTitle(this.title.getText());
    }
  }

  private void addEvent() {
    if (this.bujoFile == null) {
      this.popupWarning("Please open a .bujo file first");
    } else {
      stage.setScene(this.addEventScene);
      this.addEventController.clear();
    }
  }

  private void addTask() {
    if (this.bujoFile == null) {
      this.popupWarning("Please open a .bujo file first");
    } else {
      stage.setScene(this.addTaskScene);
      this.addTaskController.clear();
    }
  }

  private void saveMaxEvents() {
    if (this.bujoFile == null) {
      this.popupWarning("Please open a .bujo file first");
    } else {
      try {
        this.journal.getWeek().setMaxEvents(Integer.parseInt(this.maxEvents.getText()));
        this.update();
      } catch (NumberFormatException e) {
        this.popupWarning("Make sure input is a number");
      }
    }
  }

  private void saveMaxTasks() {
    if (this.bujoFile == null) {
      this.popupWarning("Please open a .bujo file first");
    } else {
      try {
        this.journal.getWeek().setMaxTasks(Integer.parseInt(this.maxTasks.getText()));
        this.update();
      } catch (NumberFormatException e) {
        this.popupWarning("Make sure input is a number");
      }
    }
  }

  private void saveNotes() {
    if (this.bujoFile == null) {
      this.popupWarning("Please open a .bujo file first");
    } else {
      this.journal.setNotes(this.notes.getText());
    }
  }

  // creates a popup with the given string warning
  private void popupWarning(String warning) {
    Rectangle background = new Rectangle(200, 100, Color.WHITE);
    background.setStroke(Color.BLACK);
    Label label = new Label(warning);
    label.setFont(Font.font(14));
    Button closeButton = new Button("Close");
    closeButton.setOnAction(event -> {
      Stage dialogStage = (Stage) closeButton.getScene().getWindow();
      dialogStage.close();
    });
    VBox layout = new VBox(10);
    layout.setMinWidth(200);
    layout.setMinHeight(100);
    layout.setAlignment(Pos.CENTER);
    layout.getChildren().addAll(label, closeButton);
    Group root = new Group();
    root.getChildren().addAll(background, layout);
    Stage dialogStage = new Stage(StageStyle.TRANSPARENT);
    dialogStage.initModality(Modality.APPLICATION_MODAL);
    dialogStage.initOwner(this.stage);
    dialogStage.setScene(new Scene(root, Color.TRANSPARENT));
    dialogStage.show();
  }

  // Gets the data stored in the bujofile and sets this journalModel to the corresponding data
  public void getData() {
    ObjectMapper mapper = new ObjectMapper();
    JsonParser parser;
    try {
      parser = mapper.createParser(this.bujoFile);
      JournalJson journalJson = parser.readValueAs(JournalJson.class);
      WeekJson weekJson = journalJson.week();
      HashMap<DayOfWeek, Day> days = this.getDayData(weekJson);
      CommitmentJson commitmentJson = weekJson.commitment();
      Week week = new Week(weekJson.title(), days,
          commitmentJson.maxTasks(), commitmentJson.maxEvents());
      this.journal.setWeek(week);
      this.journal.setTheme(Theme.valueOf(journalJson.theme()));
      this.journal.setNotes(journalJson.quotesNotes());
      parser.close();
    } catch (IOException e) {
      e.printStackTrace();
      throw new IllegalArgumentException("Error parsing through .bujo file");
    }
  }

  /**
   * Returns a map with all the days as keys and the values as the data for each day
   *
   * @param weekJson the layout of a week in json
   * @return a map with its info
   */
  public HashMap<DayOfWeek, Day> getDayData(WeekJson weekJson) {
    HashMap<DayOfWeek, Day> result = new HashMap<>();
    for (DayOfWeek day : DayOfWeek.values()) {
      ArrayList<Event> events = new ArrayList<>();
      ArrayList<Task> tasks = new ArrayList<>();
      switch (day) {
        case MONDAY -> {
          events = this.getEventsData(day, weekJson.monday());
          tasks = this.getTasksData(day, weekJson.monday());
        }
        case TUESDAY -> {
          events = this.getEventsData(day, weekJson.tuesday());
          tasks = this.getTasksData(day, weekJson.tuesday());
        }
        case WEDNESDAY -> {
          events = this.getEventsData(day, weekJson.wednesday());
          tasks = this.getTasksData(day, weekJson.wednesday());
        }
        case THURSDAY -> {
          events = this.getEventsData(day, weekJson.thursday());
          tasks = this.getTasksData(day, weekJson.thursday());
        }
        case FRIDAY -> {
          events = this.getEventsData(day, weekJson.friday());
          tasks = this.getTasksData(day, weekJson.friday());
        }
        case SATURDAY -> {
          events = this.getEventsData(day, weekJson.saturday());
          tasks = this.getTasksData(day, weekJson.saturday());
        }
        case SUNDAY -> {
          events = this.getEventsData(day, weekJson.sunday());
          tasks = this.getTasksData(day, weekJson.sunday());
        }
      }
      result.put(day, new Day(events, tasks, day));
    }
    return result;
  }

  /**
   * Returns a list of the all the event data stored in the given day
   *
   * @param day the day of the week
   * @param dayJson the json containing information
   * @return a list of the info from the json
   */
  public ArrayList<Event> getEventsData(DayOfWeek day, DayJson dayJson) {
    ArrayList<EventJson> eventJson = dayJson.events();
    ArrayList<Event> result = new ArrayList<>();
    for (EventJson event : eventJson) {
      result.add(new Event(event.name(), day, event.description(), event.length(), event.start()));
    }
    return result;
  }

  // returns a list of the all the task data stored in the given day
  public ArrayList<Task> getTasksData(DayOfWeek day, DayJson dayJson) {
    ArrayList<TaskJson> taskJson = dayJson.tasks();
    ArrayList<Task> result = new ArrayList<>();
    for (TaskJson task : taskJson) {
      result.add(new Task(task.name(), day, task.description(), task.isDone()));
    }
    return result;
  }
}
