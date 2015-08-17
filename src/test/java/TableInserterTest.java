package tejohnso.bigquery;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import com.fasterxml.jackson.jr.ob.JSON;
import java.util.regex.Pattern;

public class TableInserterTest {
  @Test
  public void itExists() {
    assertThat(TableInserter.class, notNullValue());
  }

  @Test
  public void itCreatesTables() {
    MockTablesApi  mockApi = new MockTablesApi();
    TableInserter inserter = new TableInserter(mockApi);
    inserter.insertTables(Config.config);

    assertThat(mockApi.createTableCallCount, is(1));
    assertTrue(Pattern.matches("events[0-9]{8}", mockApi.insertedTableName));
  }
}
