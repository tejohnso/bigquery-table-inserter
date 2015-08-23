## Bigquery table generator

Creates one or more tables every day as specified by the configuration file
Table names will be of the format [tableNamePrefix]YYYYMMDD

### Configuration

The configuration file `(src/main/resources/config.json)` specifies one or more table schemas each having one or more fields

```json
{
  "includeCurrentDay": true,
  "numberOfDays": 3,
  "tables": [
    {
      "projectId": "project-id",
      "dataset": "dataset",
      "tableNamePrefix": "prefix",
      "fields": [
        {
          "name": "field1",
          "type": "string",
          "nullable": false
        }
      ]
    },
  ]
}
```

### Usage

 - Set up the configuration file as required
 - Deploy to an appengine project having service account permissions to the target table project

```bash
mvn appengine:update -Dappengine.appId=project-app-id -Ddeploy.version=[version-num] -Ddeploy.module=[default | module-name]
```

### Unit Tests

```bash
mvn test
```

### Integration Tests

```bash
mvn verify

Note: Requires a json service account file.  The file should be specified by the environment variable *GOOGLE_APPLICATION_CREDENTIALS*
```
