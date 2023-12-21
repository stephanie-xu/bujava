package cs3500.pa05.view;

import cs3500.pa05.controller.AbstractController;

/**
 * View for viewing a bullet journal week
 */
public class WeekView extends AbstractView {

  /**
   * Creates the view
   * @param controller an associated controller
   */
  public WeekView(AbstractController controller) {
    super(controller);
    this.loader.setLocation(getClass().getClassLoader().getResource("main_journal.fxml"));
  }
}
