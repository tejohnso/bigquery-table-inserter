package tejohnso.bigquery;

import java.util.*;

class TableInserter {
  private final TablesApi api;

  public TableInserter(TablesApi api) {
    this.api = api;
  }

  public void insertTables(Map tableConfig) {
    List schemas = (List)tableConfig.get("tableSchemas");
    int tableCount = schemas.size();

    for (int i = 0; i < tableCount; i += 1) {
      Map tableSchema = (Map)schemas.get(i);
      tableSchema.put("name", tableSchema.get("tableNamePrefix") + getDate(0));
      api.createTable(tableSchema);
    }
  }

  private String getDate(int offset) {
    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.DAY_OF_MONTH, offset);

    String year = "" + cal.get(Calendar.YEAR);
    String month = (cal.get(Calendar.MONTH) + 1 < 10 ? "0" : "") +
    (cal.get(Calendar.MONTH) + 1);
    String day = (cal.get(Calendar.DAY_OF_MONTH) < 10 ? "0" : "") +
    cal.get(Calendar.DAY_OF_MONTH);

    return year + month + day;
  }
}
