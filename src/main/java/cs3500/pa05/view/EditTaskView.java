package cs3500.pa05.view;

import cs3500.pa05.controller.AbstractSecondaryController;

/**
 * View for updating a task
 */
public class EditTaskView extends AbstractView {

  /**
   * Creates the view
   * @param controller an associated controller
   */
  public EditTaskView(AbstractSecondaryController controller) {
    super(controller);
    this.loader.setLocation(getClass().getClassLoader().getResource("edit_task.fxml"));
  }
}