package tejohnso.bigquery;

import java.util.*;
import java.io.IOException;
import com.google.api.services.bigquery.*;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.extensions.appengine.http.UrlFetchTransport;
import com.google.api.client.googleapis.extensions.appengine.auth.oauth2.AppIdentityCredential;
import com.google.api.services.bigquery.model.*;

class BigqueryTablesApi implements TablesApi {
  private AppIdentityCredential credential;
  private Bigquery bqClient;

  public BigqueryTablesApi() {
    AppIdentityCredential credential =
    new AppIdentityCredential(Arrays.asList(BigqueryScopes.BIGQUERY));

    bqClient = new Bigquery.Builder
    (new UrlFetchTransport(), JacksonFactory.getDefaultInstance(), credential)
    .build();
  }

  public void insertTable(TableInfo tableInfo) {
    String projectId = tableInfo.projectId;
    String dataset = tableInfo.dataset;
    Table table = assembleTable(tableInfo);

    try {
      bqClient.tables().insert(projectId, dataset, table).execute();
    } catch (IOException e) {
      System.out.println(e);
    }
  }

  Table assembleTable(TableInfo tableInfo) {
    TableReference ref = new TableReference();
    ref.setTableId(tableInfo.name);
    ref.setProjectId(tableInfo.projectId);
    ref.setDatasetId(tableInfo.dataset);

    TableSchema schema = new TableSchema();
    List<TableFieldSchema> fields = new ArrayList<>();
    for (TableField field : tableInfo.fields) {
      TableFieldSchema fieldSchema = new TableFieldSchema();
      fieldSchema.setMode(field.nullable ? "NULLABLE" : "REQUIRED");
      fieldSchema.setName(field.name);
      fieldSchema.setType(field.type);
      fields.add(fieldSchema);
    }
    schema.setFields(fields);

    Table table = new Table();
    table.setTableReference(ref);
    table.setSchema(schema);
    return table;
  }
}

