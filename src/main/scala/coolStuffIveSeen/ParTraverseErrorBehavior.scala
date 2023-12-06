package coolStuffIveSeen

import cats.data.{Validated, ValidatedNel}
import cats.effect.{ExitCode, IO, IOApp}
import cats.implicits._

final case class Channel(id: Int)
final case class Result(msg: String)

object ParTraverseErrorBehavior extends IOApp {
  override def run(args: List[String]): IO[ExitCode] = {
    val channels = List(Channel(1), Channel(2))

    for {
      res <- channels.parTraverse(Logic.runLogic) // will error out and not hit number2
      _ = println(res)
    } yield ExitCode.Success


  }
}

object Logic {
  def runLogic(channel: Channel): IO[Result] = channel.id match {
    case 2 => Result("number 2").pure[IO]
    case _ => IO.raiseError(new Error("throw error"))
  }
}

object ValidatedParTraverseErrorBehavior extends IOApp {
  override def run(args: List[String]): IO[ExitCode] = {
    val channels = List(Channel(1), Channel(2))
    for {
      validatedRes <- channels.parTraverse(ValidatedLogic.runLogic)
      res = validatedRes.flatMap(_.toList)
      _ = println(res)
    } yield ExitCode.Success
  }
}

object ValidatedLogic {
  def runLogic(channel: Channel): IO[ValidatedNel[Throwable, Result]] = channel.id match {
    case 2 => IO(Validated.valid(Result("number 2")))
    case _ => IO(Validated.invalidNel(new Error("throw error")))
  }
}