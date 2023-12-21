package cs3500.pa05.view;

import cs3500.pa05.controller.AbstractSecondaryController;

/**
 * View for adding a task
 */
public class AddTaskView extends AbstractView {

  /**
   * Creates the view
   * @param controller an associated controller
   */
  public AddTaskView(AbstractSecondaryController controller) {
    super(controller);
    this.loader.setLocation(getClass().getClassLoader().getResource("add_task.fxml"));
  }
}
