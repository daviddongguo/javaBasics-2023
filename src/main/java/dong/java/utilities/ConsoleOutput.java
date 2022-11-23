package dong.java.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConsoleOutput {


  private static final Logger LOGGER = LogManager.getLogger(ConsoleOutput.class);

  public static void println(String message) {
    LOGGER.debug(message);
  }

  private ConsoleOutput() {
  }
}
