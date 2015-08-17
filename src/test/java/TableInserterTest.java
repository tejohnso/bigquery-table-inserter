package tejohnso.bigquery;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TableInserterTest {
  @Test
  public void existenceTest() {
    assertThat(TableInserter.class, notNullValue());
  }

  @Test
  public void failTest() {
    fail();
  }
}
