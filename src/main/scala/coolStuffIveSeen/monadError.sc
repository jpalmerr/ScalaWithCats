import java.util.Date

import cats.effect.Sync
import cats.{Applicative, MonadError}
import cats.implicits._
import cats.mtl.implicits._
import cats.mtl.{ApplicativeHandle, FunctorRaise}

case class User(name: String, userName: String)

sealed trait AuthenticationError
case object WrongUserName extends AuthenticationError
case object WrongPassword extends AuthenticationError
final case class ExpiredSubscription(expirationDate: Date) extends AuthenticationError
case object BannedUser extends AuthenticationError

def findUserByName[F[_]](name: String)(
  implicit ME: MonadError[F, Throwable]
): F[User] = ME.raiseError(new RuntimeException("The database cannot be reached"))

def checkPassword[F[_]](user: User, password: String)(
  implicit FR: FunctorRaise[F, AuthenticationError],
  A: Applicative[F]
): F[Unit] = if (password == "1234") A.unit else FR.raise(WrongPassword)

def checkSubscription[F[_]](user: User): F[Unit] = ???
def checkUserStatus[F[_]](user: User): F[Unit] = ???

def authenticate[F[_]](userName: String, password: String)(
  // We depend on the requirements of our intermediate methods
  implicit ME: MonadError[F, Throwable],
  functorRaise: FunctorRaise[F, AuthenticationError]
): F[User] =
  for {
    user <- findUserByName[F](userName)
    _ <- checkPassword[F](user, password)
    _ <- checkSubscription[F](user)
    _ <- checkUserStatus[F](user)
  } yield user

def authenticateAndHandle[F[_]](
                                 implicit ME: MonadError[F, Throwable],
                                 AE: ApplicativeHandle[F, AuthenticationError],
                                 // `Sync` is the type class that describes the ability to suspend side effects
                                 // `IO` provides a concrete instance of Sync
                                 Sync: Sync[F]
                               ) =
  authenticate("john.doe", "123456")
    // Handle business errors
    .handleWith({
      case WrongUserName => Sync.delay { /* Do stuff ... */ }
      case WrongPassword => Sync.delay { /* Do stuff ... */ }
      case e: AuthenticationError => Sync.delay {
        println("Another domain error was caught !")
      }
    })
    // Handle technical failures
    .recoverWith({
      case e: Throwable => Sync.delay {
        println("Something went terribly wrong!")
      }
    })
