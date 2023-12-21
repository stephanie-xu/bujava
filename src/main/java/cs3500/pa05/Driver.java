package cs3500.pa05;

import cs3500.pa05.controller.AddEventController;
import cs3500.pa05.controller.AddTaskController;
import cs3500.pa05.controller.EditEventController;
import cs3500.pa05.controller.EditTaskController;
import cs3500.pa05.controller.WeekController;
import cs3500.pa05.model.JournalModel;
import cs3500.pa05.view.AbstractView;
import cs3500.pa05.view.AddEventView;
import cs3500.pa05.view.AddTaskView;
import cs3500.pa05.view.EditEventView;
import cs3500.pa05.view.EditTaskView;
import cs3500.pa05.view.WeekView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class to run the bullet journal
 */
public class Driver extends Application {

   /**
     * Runs the bullet journal
     *
     * @param stage the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     */
  public void start(Stage stage) {
    JournalModel journal = new JournalModel();
    WeekController weekController = new WeekController(stage, journal);
    AbstractView weekView = new WeekView(weekController);
    Scene weekScene = weekView.load();
    AddEventController addEventController = new AddEventController(stage, journal,
        weekController, weekScene);
    AbstractView addEventView = new AddEventView(addEventController);
    Scene addEventScene = addEventView.load();
    AddTaskController addTaskController = new AddTaskController(stage, journal,
        weekController, weekScene);
    AbstractView addTaskView = new AddTaskView(addTaskController);
    Scene addTaskScene = addTaskView.load();
    EditEventController editEventController = new EditEventController(stage, journal,
        weekController, weekScene);
    AbstractView editEventView = new EditEventView(editEventController);
    Scene editEventScene = editEventView.load();
    EditTaskController editTaskController = new EditTaskController(stage, journal,
        weekController, weekScene);
    AbstractView editTaskView = new EditTaskView(editTaskController);
    Scene editTaskScene = editTaskView.load();
    weekController.setAddEventScene(addEventScene);
    weekController.setAddTaskScene(addTaskScene);
    weekController.setEditEventScene(editEventScene);
    weekController.setEditTaskScene(editTaskScene);
    weekController.setAddEventController(addEventController);
    weekController.setAddTaskController(addTaskController);
    weekController.setEditEventController(editEventController);
    weekController.setEditTaskController(editTaskController);
    weekController.init();
    addEventController.init();
    addTaskController.init();
    editEventController.init();
    editTaskController.init();
    try {
      stage.setScene(weekScene);
      stage.setTitle("Journal");
      stage.show();
    } catch (IllegalStateException exc) {
      System.err.println("Unable to load GUI.");
    }
  }

  /**
   * Runs a GUI application.
   *
   * @param args the command-line arguments
   */
  public static void main(String[] args) {
    launch();
  }
}
