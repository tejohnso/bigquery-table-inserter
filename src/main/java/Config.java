package tejohnso.bigquery;

import com.fasterxml.jackson.jr.ob.JSON;
import java.util.*;
import java.io.InputStream;
import java.util.logging.Logger;

class Config {
  static final InputStream configFile = ClassLoader.getSystemResourceAsStream
  ("config.json");

  private static final Logger log = Logger.getAnonymousLogger();
  static boolean includeCurrentDay;
  static int numberOfDays;
  static List<TableInfo> tables;

  static {
    try {
      Map config = (Map)(JSON.std.anyFrom(configFile));
      includeCurrentDay = (boolean)config.get("includeCurrentDay");
      numberOfDays = (int)config.get("numberOfDays");
      tables = new ArrayList<TableInfo>();
      for (Object info : (List)config.get("tables")) {
        Map mapInfo = (Map)info;
        TableInfo tableInfo = new TableInfo();
        tableInfo.projectId = (String)mapInfo.get("projectId");
        tableInfo.dataset = (String)mapInfo.get("dataset");
        tableInfo.tableNamePrefix = (String)mapInfo.get("tableNamePrefix");
        tableInfo.fields = new ArrayList<TableField>();
        for (Object fieldSet : (List)mapInfo.get("fields")) {
          TableField tf = new TableField();
          Map fs = (Map)fieldSet;
          tf.name = (String)fs.get("name");
          tf.type = (String)fs.get("type");
          tf.nullable = (boolean)fs.get("nullable");
          tableInfo.fields.add(tf);
        }
        tables.add(tableInfo);
      }
    } catch (final Exception ex) {
      log.severe(ex.getMessage());
      throw new RuntimeException("Could not initialize configuration");
    }
  }
}

class TableInfo {
  String projectId;
  String dataset;
  String tableNamePrefix;
  List<TableField> fields;
  String name;
}

class TableField {
  String name;
  String type;
  boolean nullable;
}
