package cs3500.pa05.view;

import cs3500.pa05.controller.AbstractSecondaryController;

/**
 * View for editing an event
 */
public class EditEventView extends AbstractView {

  /**
   * Creates the view
   * @param controller an associated controller
   */
  public EditEventView(AbstractSecondaryController controller) {
    super(controller);
    this.loader.setLocation(getClass().getClassLoader().getResource("edit_event.fxml"));
  }
}