package dong.java;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StandardOutputStream {

  private static final Logger LOGGER = LogManager.getLogger(StandardOutputStream.class);

  public static void println(String message) {
    LOGGER.info(message);
  }

  private StandardOutputStream() {
  }
}
