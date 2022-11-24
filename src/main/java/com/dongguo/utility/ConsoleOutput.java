package com.dongguo.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConsoleOutput {


  private static final Logger LOGGER = LogManager.getLogger(ConsoleOutput.class);

  private ConsoleOutput() {
  }

  public static void println(String message) {
    LOGGER.debug(message);
  }
}
