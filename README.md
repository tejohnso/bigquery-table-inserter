## Bigquery table generator

Creates one or more tables every day as specified by the configuration file
Table names will be of the format [tableNamePrefix]YYYYMMDD

### Configuration

The configuration file specifies one or more table schemas each having one or more fields

```json
{
  "includeCurrentDay": true,
  "numberOfDays": 3,
  "tableSchemas": [
    {
      "dataset": "Viewer_Events",
      "tableNamePrefix": "events",
      "fields": [
        {
          "name": "event",
          "type": "string",
          "nullable": false
        }
      ]
    }
  ]
}
```

### Usage

 - Set up the configuration file as required
 - Deploy to an appengine project having service account permissions to the target table project
