```scala
// Logging slow queries (and exceptions) in doobie:


 private val SlowThreshold = 200.millis
​
  /**
    * Logs the SQL queries which are slow or end up in an exception.
  */
  implicit val doobieLogHandler: LogHandler = LogHandler {
    case Success(sql, _, exec, processing) =>
      if (exec > SlowThreshold || processing > SlowThreshold) {
        warn(s"Slow query (execution: $exec, processing: $processing): $sql")
      }
    case ProcessingFailure(sql, args, exec, processing, failure) =>
      error(s"Processing failure (execution: $exec, processing: $processing): $sql | args: $args", failure)
    case ExecFailure(sql, args, exec, failure) =>
      error(s"Execution failure (execution: $exec): $sql | args: $args", failure)
  }
```