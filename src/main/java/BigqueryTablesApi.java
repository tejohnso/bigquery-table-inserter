package tejohnso.bigquery;

import java.util.*;
import com.google.api.services.bigquery.*;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.extensions.appengine.http.UrlFetchTransport;
import com.google.api.client.googleapis.extensions.appengine.auth.oauth2.AppIdentityCredential;

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

  public int createTable(Map tableInfo) {
    return 1;
  }
}

