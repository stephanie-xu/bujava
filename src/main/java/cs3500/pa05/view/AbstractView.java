package cs3500.pa05.view;

import cs3500.pa05.controller.AbstractController;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

/**
 * Abstract View with methods for all of the View classes
 */
public abstract class AbstractView implements View {
  protected FXMLLoader loader;

  AbstractView(AbstractController controller) {
    this.loader = new FXMLLoader();
    this.loader.setController(controller);
  }

  /**
   * Returns the scene from the file given
   *
   * @return the scene
   * @throws IllegalStateException if unable to provide scene
   */
  public Scene load() throws IllegalStateException {
    try {
      return this.loader.load();
    } catch (IOException exc) {
      throw new IllegalStateException("Unable to load layout.");
    }
  }
}
