package zio.validationPattern

import zio.ZIO

case class Error(message: String)

case class ValidationResult[Item, Success](
                                            successes: List[Success],
                                            failures: List[(Item, Error)]
                                          ) {
  def logFailures(
                   itemName: Item => String,
                   operationName: String
                 ): ZIO[Any, Nothing, Unit] =
    if (failures.nonEmpty) {
      val failureDetails = failures.map { case (item, failure) =>
        s"${itemName(item)}: ${failure.message}"
      }.mkString(", ")

      ZIO.unit  // .logWarning(s"$operationName completed with ${failures.size} failures: $failureDetails")
    } else {
      ZIO.unit
//      ZIO.logInfo(s"$operationName completed successfully for all ${successes.size} items")
    }
}

object Helpers {
  def processAndCollectResultsPar[Item, Success](
  items: List[Item]
  )(process: Item => ZIO[Any, Error, Success]): ZIO[Any, Nothing, ValidationResult[Item, Success]] =
    ZIO
      .foreachPar(items) { item =>
        process(item).either.map(item -> _)
      }
      .map { results =>
        val (failures, successes) = results.partitionMap {
          case (item, Left(failure)) => Left((item, failure))
          case (_, Right(success))   => Right(success)
        }
        ValidationResult(successes, failures)
      }
}

/**
 * Returns an effect whose failure and success have been lifted into an
 * `Either`.The resulting effect cannot fail, because the failure case has
 * been exposed as part of the `Either` success case.
 *
 * This method is useful for recovering from `ZIO` effects that may fail.
 *
 * The error parameter of the returned `ZIO` is `Nothing`, since it is
 * guaranteed the `ZIO` effect does not model failure.

 final def either(implicit ev: CanFail[E]): URIO[R, Either[E, A]] =
    self.foldM(ZIO.succeedLeft, ZIO.succeedRight)
 */
