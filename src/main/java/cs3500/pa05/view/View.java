package cs3500.pa05.view;

import java.io.IOException;
import javafx.scene.Scene;

/**
 * View interface with all the methods the class that extend view require
 */
public interface View {
  /**
   * Attempts to set the scene from the loader's file
   *
   * @return the scene
   * @throws IOException if unable to load
   */
  Scene load() throws IOException;
}
