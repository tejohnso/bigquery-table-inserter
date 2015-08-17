package tejohnso.bigquery;

import java.util.Map;

class MockTablesApi implements TablesApi {
  int createTableCallCount = 0;
  String insertedTableName;

  public int createTable(Map tableInfo) {
    createTableCallCount += 1;
    insertedTableName = (String)tableInfo.get("name");
    return 1;
  }
}
