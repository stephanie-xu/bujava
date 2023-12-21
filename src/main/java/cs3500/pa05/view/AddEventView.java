package cs3500.pa05.view;

import cs3500.pa05.controller.AbstractSecondaryController;

/**
 * View for adding an event
 */
public class AddEventView extends AbstractView {
  /**
   * Creates the view
   * @param controller an associated controller
   */
  public AddEventView(AbstractSecondaryController controller) {
    super(controller);
    this.loader.setLocation(getClass().getClassLoader().getResource("add_event.fxml"));
  }
}
