package tejohnso.bigquery;

import java.util.List;

class Config {
  boolean includeCurrentDay;
  int numberOfDays;
  List<TableInfo> tables;

  class TableInfo {
    String projectId;
    String dataset;
    String tableNamePrefix;
    String name;
    List<TableField> fields;
  }

  class TableField {
    String name;
    String type;
    boolean nullable;
  }
}
