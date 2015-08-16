package tejohnso.bigquery;

import com.fasterxml.jackson.jr.ob.JSON;
import java.util.Map;
import java.io.InputStream;
import java.util.logging.Logger;

class Config {
  static final InputStream configFile = ClassLoader.getSystemResourceAsStream
  ("config.json");

  static final Map config;
  static final Logger log = Logger.getAnonymousLogger();

  static {
    try {
      config = (Map)(JSON.std.anyFrom(configFile));
    } catch (final Exception ex) {
      log.severe(ex.getMessage());
      throw new RuntimeException("Could not initialize configuration");
    }
  }
}
